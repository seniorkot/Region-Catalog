package com.seniorkot.regioncatalog.service.impl;

import com.seniorkot.regioncatalog.service.RegionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
}
