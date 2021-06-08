package com.seniorkot.regioncatalog.utils.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * Interface with method to copy request params to model entity.
 *
 * @param <T> Model Request class
 *
 * @author seniorkot
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public interface CopyFromRequest<T> extends Serializable {

    /**
     * Copies request params to model.
     *
     * @param request Request params.
     */
    void copy(@NonNull T request);
}
