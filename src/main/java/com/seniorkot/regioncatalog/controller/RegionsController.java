package com.seniorkot.regioncatalog.controller;

import com.seniorkot.regioncatalog.domain.model.Region;
import com.seniorkot.regioncatalog.domain.model.request.RegionRequest;
import com.seniorkot.regioncatalog.service.RegionService;
import com.seniorkot.regioncatalog.utils.response.CodeMessageResponse;
import com.seniorkot.regioncatalog.utils.response.CodeMessageResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    private static final Logger logger = LogManager.getLogger(RegionsController.class);

    private final RegionService regionService;

    @Autowired
    public RegionsController(RegionService regionService) {
        this.regionService = regionService;
    }

    /**
     * Returns all regions.
     *
     * @param name Region name
     * @return <b>Response code</b>: 200<br>
     *      <b>Body</b>: {@link List} of {@link Region} objects
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeMessageResponse<List<Region>> getRegions(@RequestParam(required = false) String name) {
        logger.debug("getRegions: name=" + name);
        CodeMessageResponse<List<Region>> response =
                CodeMessageResponseBuilder.ok(regionService.getAll(name));
        logger.debug("getRegions: response=" + response.getBody());
        return response;
    }

    /**
     * Returns a region by ID.
     *
     * @param id Region ID
     * @return <b>Response code</b>: 200<br>
     *      <b>Body</b>: {@link Region} object
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeMessageResponse<Region> getRegion(@PathVariable Long id) {
        logger.debug("getRegion: id=" + id);
        CodeMessageResponse<Region> response =
                CodeMessageResponseBuilder.ok(regionService.get(id));
        logger.debug("getRegion: response=" + response.getBody());
        return response;
    }

    /**
     * Creates new region.
     *
     * @param request {@link RegionRequest} with name and short name
     * @return <b>Response code</b>: 201<br>
     *      <b>Body</b>: {@link URI} of newly created object
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeMessageResponse<URI> createRegion(@RequestBody RegionRequest request,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        logger.debug("createRegion: request=" + request);
        final long regionId = regionService.create(request);
        UriComponents uriComponents =
                uriComponentsBuilder.path(CONTROLLER_PATH + "/{id}").buildAndExpand(regionId);
        CodeMessageResponse<URI> response = CodeMessageResponseBuilder.created(uriComponents.toUri());
        logger.debug("createRegion: response=" + response.getBody());
        return response;
    }

    /**
     * Updates a region by ID.
     *
     * @param id Region ID
     * @param request {@link RegionRequest} with name and short name
     * @return <b>Response code</b>: 204
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeMessageResponse<String> updateRegion(@PathVariable Long id,
                                                    @RequestBody RegionRequest request) {
        logger.debug("updateRegion: id=" + id + "; request=" + request);
        regionService.update(id, request);
        CodeMessageResponse<String> response =
                CodeMessageResponseBuilder.noContent();
        logger.debug("updateRegion: response=" + response.getBody());
        return response;
    }

    /**
     * Removes a region by ID.
     *
     * @param id Region ID
     * @return <b>Response code</b>: 204
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeMessageResponse<String> removeRegion(@PathVariable Long id) {
        logger.debug("removeRegion: id=" + id);
        regionService.remove(id);
        CodeMessageResponse<String> response =
                CodeMessageResponseBuilder.noContent();
        logger.debug("removeRegion: response=" + response.getBody());
        return response;
    }
}
