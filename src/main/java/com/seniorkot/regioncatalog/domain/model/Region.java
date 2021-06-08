package com.seniorkot.regioncatalog.domain.model;

import com.seniorkot.regioncatalog.domain.model.request.RegionRequest;
import com.seniorkot.regioncatalog.utils.interfaces.CopyFromRequest;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * Region entity class.
 *
 * @author seniorkot
 */
@Data
public class Region implements CopyFromRequest<RegionRequest> {

    /**
     * Region ID.
     */
    private Long id;

    /**
     * Region full name.
     */
    private String name;

    /**
     * Region short name.
     */
    private String shortName;

    @Override
    public void copy(@NonNull RegionRequest request) {
        this.name = request.getName();
        this.shortName = request.getShortName();
    }
}
