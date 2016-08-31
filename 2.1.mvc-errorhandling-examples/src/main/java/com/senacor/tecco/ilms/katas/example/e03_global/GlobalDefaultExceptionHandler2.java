package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.exceptions.ErrorMessageComposer;
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
 * Here there are two points. First it shows how ResponseEntityExceptionHandler can be extended and with its
 * handleExceptionInternal method we can get the HttpStatus of some of the standard spring MVC exception.(shown below)
 * The second point is @Order annotation and how it can be used to override behavior of other @ControllerAdvice annotated
 * methods. A helpful stackoverflow question
 * http://stackoverflow.com/questions/19498378/setting-precedence-of-multiple-controlleradvice-exceptionhandlers
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
