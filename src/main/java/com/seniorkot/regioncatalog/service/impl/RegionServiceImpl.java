package com.seniorkot.regioncatalog.service.impl;

import com.seniorkot.regioncatalog.domain.mapper.RegionMapper;
import com.seniorkot.regioncatalog.domain.model.Region;
import com.seniorkot.regioncatalog.domain.model.request.RegionRequest;
import com.seniorkot.regioncatalog.service.RegionService;
import com.seniorkot.regioncatalog.utils.exception.BadInputDataException;
import com.seniorkot.regioncatalog.utils.exception.EntityAlreadyExistsException;
import com.seniorkot.regioncatalog.utils.exception.EntityNotFoundException;
import com.seniorkot.regioncatalog.utils.exception.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public List<Region> getAll(@Nullable String name) {
        return name == null ? regionMapper.getAll() :
                regionMapper.getAllByName(name);
    }

    @Override
    public Region get(@NonNull Long id) throws EntityNotFoundException {
        return regionMapper.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(Region.class, id));
    }

    @Override
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
    public void remove(@NonNull Long id) throws EntityNotFoundException {
        regionMapper.delete(get(id));
    }
}
