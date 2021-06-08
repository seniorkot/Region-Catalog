package com.seniorkot.regioncatalog.controller;

import com.seniorkot.regioncatalog.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Regions REST controller class.
 *
 * @author seniorkot
 */
@RestController
@RequestMapping(value = RegionsController.CONTROLLER_PATH)
public class RegionsController {

    /**
     * Controller path value.
     */
    public static final String CONTROLLER_PATH = "/regions";

    private final RegionService regionService;

    @Autowired
    public RegionsController(RegionService regionService) {
        this.regionService = regionService;
    }

}
