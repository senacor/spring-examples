/*

package com.exercise;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.Exception;


*/
/**
 * Created by amishra on 12.03.2016
 * -------------------------------------------------------------------------------------------
 * Second Class to define an Exception Handler .
 * -------------------------------------------------------------------------------------------
 * 1. Use Controller Advice to give this particular class the highest priority.
 * 2. Use proper annotations for the "handleException" method so that it can handle exceptions.
 *//*


public class GlobalExceptionHandler2 extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(java.lang.Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
        headers.set("key", "value");
        HttpStatus returnStatus = status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        BaseResponse errorMessage = createErrorMessage(ex);

        // Use the HTTP status set in the UserNotFoundException
        ResponseStatus exResponseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (exResponseStatus != null) {
            returnStatus = exResponseStatus.value();
        }
        return new ResponseEntity<>(errorMessage, headers, returnStatus);

    }

    public ResponseEntity<Object> handleException(HttpServletRequest request, java.lang.Exception e) throws java.lang.Exception {
        return this.handleExceptionInternal(e, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public BaseResponse createErrorMessage(Exception ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message("unexpected_exception", Severity.ERROR);
        message.setMessage(ex.getMessage());
        //message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }
}



*/
/**
 * SPRING MVC EXCEPTIONS
 * ===================================================================
 * BindException                           400 - Bad Request
 * ConversionNotSupportedException         500 - Internal Server Error
 * HttpMediaTypeNotAcceptableException     406 - Not Acceptable
 * HttpMediaTypeNotSupportedException      415 - Unsupported Media Type
 * HttpMessageNotReadableException         400 - Bad Request
 * HttpMessageNotWritableException         500 - Internal Server Error
 * HttpRequestMethodNotSupportedException  405 - Method Not Allowed
 * MethodArgumentNotValidException         400 - Bad Request
 * MissingServletRequestParameterException 400 - Bad Request
 * MissingServletRequestPartException      400 - Bad Request
 * NoSuchRequestHandlingMethodException    404 - Not Found
 * TypeMismatchException                   400 - Bad Request
 *//*


*/
