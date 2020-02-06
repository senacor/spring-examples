package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fsubasi on 17.01.2016
 *
 * This global exception handler handles NumberFormatException and NullPointerExceptions. Since the other
 * controller handles NullPointerExceptions too and has a higher priority, the method in this exception handler
 * will never be executed
 */

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler2 {

    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorResponse handleRuntimeException(HttpServletResponse response, Exception ex) {
        // Set headers
        response.addHeader("key", "value");

        ResponseStatus exResponseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (exResponseStatus != null) {
            HttpStatus returnStatus = exResponseStatus.value();
            response.setStatus(returnStatus.value());
        }
        return createErrorMessage(ex);

    }

    public ErrorResponse createErrorMessage(Exception e) {
        return new ErrorResponse("exception_handled_by_global_handler_2", e.getMessage());
    }
}
