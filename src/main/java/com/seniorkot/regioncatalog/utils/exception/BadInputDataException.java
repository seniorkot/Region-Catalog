package com.seniorkot.regioncatalog.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception thrown when input data is bad.
 *
 * @author seniorkot
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadInputDataException extends RuntimeException {

    public BadInputDataException() {
        super();
    }

    public BadInputDataException(String message) {
        super(message);
    }

    public BadInputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

