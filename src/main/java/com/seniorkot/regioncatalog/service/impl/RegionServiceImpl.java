package com.seniorkot.regioncatalog.service.impl;

import com.seniorkot.regioncatalog.controller.RegionsController;
import com.seniorkot.regioncatalog.domain.mapper.RegionMapper;
import com.seniorkot.regioncatalog.domain.model.Region;
import com.seniorkot.regioncatalog.domain.model.request.RegionRequest;
import com.seniorkot.regioncatalog.service.RegionService;
import com.seniorkot.regioncatalog.utils.exception.BadInputDataException;
import com.seniorkot.regioncatalog.utils.exception.EntityAlreadyExistsException;
import com.seniorkot.regioncatalog.utils.exception.EntityNotFoundException;
import com.seniorkot.regioncatalog.utils.exception.InternalServerErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link RegionService}.
 *
 * @author seniorkot
 */
@Primary
@Service(value = RegionServiceImpl.SERVICE_VALUE)
public class RegionServiceImpl implements RegionService {

    /**
     * Service implementation value.
     */
    public static final String SERVICE_VALUE = "ProductServiceImpl";

    private static final Logger logger = LogManager.getLogger(RegionServiceImpl.class);

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    @Cacheable(value = "regions", key = "#name")
    public List<Region> getAll(@Nullable String name) {
        logger.debug("Getting all regions with name starts with " + name);
        return name == null ? regionMapper.getAll() :
                regionMapper.getAllByName(name + "%"); // Starts with statement
    }

    @Override
    @Cacheable(value = "regions", key = "#id")
    public Region get(@NonNull Long id) throws EntityNotFoundException {
        logger.debug("Getting region by ID " + id);
        return regionMapper.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(Region.class, id));
    }

    @Override
    @CachePut("regions")
    public Long create(@NonNull RegionRequest request)
            throws EntityAlreadyExistsException, BadInputDataException {
        List<String> blankFields = request.getBlankRequiredFields(); // Get blank fields
        if (blankFields.size() > 0) {
            throw new BadInputDataException(String.join(", ", blankFields)
                    + " of RegionRequest are missing");
        }
        if (regionMapper.getByNames(request.getName(), request.getShortName()).isPresent()) {
            throw new EntityAlreadyExistsException("Region (" + request.getName() + "; "
                    + request.getShortName() + ") already exists");
        }
        Region region = new Region();
        region.copy(request);
        regionMapper.insert(region);
        return regionMapper.getByNames(request.getName(), request.getShortName())
                .orElseThrow(() -> new InternalServerErrorException("Couldn't save region entity")).getId();
    }

    @Override
    @CachePut("regions")
    public void update(@NonNull Long id, @NonNull RegionRequest request)
            throws EntityNotFoundException, EntityAlreadyExistsException, BadInputDataException {
        List<String> blankFields = request.getBlankRequiredFields(); // Get blank fields
        if (blankFields.size() > 0) {
            throw new BadInputDataException(String.join(", ", blankFields)
                    + " of RegionRequest are missing");
        }
        Region region = get(id);
        if (!region.getName().equals(request.getName())
                && !region.getShortName().equals(request.getShortName())
                || regionMapper.getByNames(request.getName(), request.getShortName()).isPresent()) {
            throw new EntityAlreadyExistsException("Region (" + request.getName() + "; "
                    + request.getShortName() + ") already exists");
        }
        region.copy(request);
        regionMapper.update(region);
    }

    @Override
    @CacheEvict("regions")
    public void remove(@NonNull Long id) throws EntityNotFoundException {
        regionMapper.delete(get(id));
    }
}
