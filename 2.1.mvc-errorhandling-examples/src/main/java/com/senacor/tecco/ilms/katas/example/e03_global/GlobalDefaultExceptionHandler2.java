package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.response.ErrorMessageComposer;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 17.01.2016
 *
 * There are two important observations in this example:
 * 1) It shows how ResponseEntityExceptionHandler can be extended and with its handleExceptionInternal
 * method we can get the HttpStatus of some of the standard spring MVC exception.
 * 2) This global exception handler handles NumberFormatException and NullPointerExceptions. Since the other
 * controller handles NullPointerExceptions too and has a higher priority, the method in this exception handler
 * will never be executed
 */

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler2 extends ResponseEntityExceptionHandler{

    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(HttpServletRequest request, Exception e) throws Exception{
        return this.handleExceptionInternal(e, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
        // Set headers
        headers.set("key", "value");
        HttpStatus returnStatus = status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        BaseResponse errorMessage = createErrorMessage(ex);

        // If the custom exception is annotated with @ResponseStatus, use it
        ResponseStatus exResponseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (exResponseStatus != null) {
            returnStatus = exResponseStatus.value();
        }
        return new ResponseEntity<>(errorMessage, headers, returnStatus);
    }

    public BaseResponse createErrorMessage(Exception e) {
        return ErrorMessageComposer.messageComposer(e, "exception_handled_by_global_handler_2");
    }
}
