package com.seniorkot.regioncatalog.controller.handler;

import com.seniorkot.regioncatalog.utils.exception.*;
import com.seniorkot.regioncatalog.utils.response.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * Controller advice class to catch all custom 4xx and 5xx exceptions.
 *
 * @author seniorkot
 */
@ControllerAdvice
public class HttpErrorExceptionHandler {

    private static final Logger logger = LogManager.getLogger(HttpErrorExceptionHandler.class);

    /**
     * Exception handler method for custom {@link RuntimeException} realizations.<br>
     * Called when {@link BadInputDataException} is thrown during any controller
     * method execution.
     *
     * @param ex Thrown exception
     * @param request Incoming request
     * @return <b>Response</b>: 200<br>
     *     <b>Body</b>: {@link CodeMessage} object (code: <i>400</i>)
     */
    @ExceptionHandler(BadInputDataException.class)
    protected CodeMessageResponse<?> handleBadRequest(RuntimeException ex, WebRequest request) {
        logger.info("Exception: " + request.getDescription(false) + "; message=" + ex.getMessage());
        return CodeMessageResponseBuilder.badRequest(ex.getMessage());
    }

    /**
     * Exception handler method for custom {@link RuntimeException} realizations.<br>
     * Called when {@link EntityNotFoundException} is thrown during any controller method execution.
     *
     * @param ex Thrown exception
     * @param request Incoming request
     * @return <b>Response</b>: 200<br>
     *     <b>Body</b>: {@link CodeMessage} object (code: <i>404</i>)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected CodeMessageResponse<?> handleNotFound(RuntimeException ex, WebRequest request) {
        logger.info("Exception: " + request.getDescription(false) + "; message=" + ex.getMessage());
        return CodeMessageResponseBuilder.notFound(ex.getMessage());
    }

    /**
     * Exception handler method for custom {@link RuntimeException} realizations.<br>
     * Called when {@link EntityAlreadyExistsException} is thrown during any controller method execution.
     *
     * @param ex Thrown exception
     * @param request Incoming request
     * @return <b>Response</b>: 200<br>
     *     <b>Body</b>: {@link CodeMessage} object (code: <i>409</i>)
     */
    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected CodeMessageResponse<?> handleConflict(RuntimeException ex, WebRequest request) {
        logger.info("Exception: " + request.getDescription(false) + "; message=" + ex.getMessage());
        return CodeMessageResponseBuilder.conflict(ex.getMessage());
    }

    /**
     * Exception handler method for {@link NumberFormatException}.<br>
     * Called when {@link NumberFormatException} is thrown during any controller method execution.
     *
     * @param ex Thrown exception
     * @param request Incoming request
     * @return <b>Response</b>: 200<br>
     *     <b>Body</b>: {@link CodeMessage} object (code: <i>400</i>)
     */
    @ExceptionHandler(NumberFormatException.class)
    protected CodeMessageResponse<?> handleNumberFormatException(RuntimeException ex, WebRequest request) {
        logger.info("Exception: " + request.getDescription(false)
                + "; message=Provided ID is not a numeric value");
        return CodeMessageResponseBuilder.badRequest("Provided ID is not a numeric value");
    }

    /**
     * Exception handler method for {@link InternalServerErrorException}.<br>
     * Called when {@link InternalServerErrorException} is thrown during any controller method execution.
     *
     * @param ex Thrown exception
     * @param request Incoming request
     * @return <b>Response</b>: 200<br>
     *     <b>Body</b>: {@link CodeMessage} object (code: <i>500</i>)
     */
    @ExceptionHandler(InternalServerErrorException.class)
    protected CodeMessageResponse<?> handleInternalServerErrors(RuntimeException ex, WebRequest request) {
        logger.error("Exception: " + request.getDescription(false) + "; message=" + ex.getMessage());
        return CodeMessageResponseBuilder.internal(ex.getMessage());
    }

}
