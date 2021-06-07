package com.seniorkot.regioncatalog.domain.model;

import lombok.Data;

/**
 * Region entity class.
 *
 * @author seniorkot
 */
@Data
public class Region {

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
}
