package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fsubasi on 17.02.2016.
 *
 * This GlobalDefaultExceptionHandler handles NullPointerExceptions
 * In the case of a multiple controller advices handling same exception,
 * the controller with the highest precedence will handle the exception.
 */

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler1 {

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return createErrorMessage(e);
    }

    public ErrorResponse createErrorMessage(Exception e) {
        return new ErrorResponse("exception_handled_by_global_handler_1", e.getMessage());
    }
}
