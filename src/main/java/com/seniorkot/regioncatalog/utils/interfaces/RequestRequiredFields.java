package com.seniorkot.regioncatalog.utils.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for checking required fields of request POJOs.
 *
 * @author seniorkot
 */
public interface RequestRequiredFields extends Serializable {

    /**
     * Checks required fields of request POJO.
     *
     * @return {@link List} with unfilled required fields.
     */
    @JsonIgnore
    List<String> getBlankRequiredFields();
}
