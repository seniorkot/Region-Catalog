package com.seniorkot.regioncatalog.service;

import com.seniorkot.regioncatalog.domain.model.Region;
import com.seniorkot.regioncatalog.domain.model.request.RegionRequest;
import com.seniorkot.regioncatalog.utils.exception.BadInputDataException;
import com.seniorkot.regioncatalog.utils.exception.EntityAlreadyExistsException;
import com.seniorkot.regioncatalog.utils.exception.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link com.seniorkot.regioncatalog.domain.model.Region} service.
 *
 * @author seniorkot
 */
@Service
public interface RegionService {

    /**
     * Returns a list of all regions.
     *
     * @param name Filter: name start
     * @return {@link List} of {@link Region} entities
     */
    List<Region> getAll(@Nullable String name);

    /**
     * Returns a region by ID.
     *
     * @param id Region ID
     * @return {@link Region} entity
     * @throws EntityNotFoundException No region with such ID
     */
    Region get(@NonNull Long id) throws EntityNotFoundException;

    /**
     * Creates new region.
     *
     * @param request {@link RegionRequest} with name and short name
     * @return ID of newly created region
     * @throws EntityAlreadyExistsException Entity with such params already exists
     * @throws BadInputDataException Bad name and/or short name
     */
    Long create(@NonNull RegionRequest request)
            throws EntityAlreadyExistsException, BadInputDataException;

    /**
     * Updates an existing region by ID.
     *
     * @param id Region ID
     * @param request {@link RegionRequest} with name and short name
     * @throws EntityNotFoundException No region with such ID
     * @throws EntityAlreadyExistsException Entity with such params already exists
     * @throws BadInputDataException Bad name and/or short name
     */
    void update(@NonNull Long id, @NonNull RegionRequest request)
            throws EntityNotFoundException, EntityAlreadyExistsException, BadInputDataException;

    /**
     * Removes a region by ID.
     *
     * @param id Region ID
     * @throws EntityNotFoundException No region with such ID
     */
    void remove(@NonNull Long id) throws EntityNotFoundException;
}
