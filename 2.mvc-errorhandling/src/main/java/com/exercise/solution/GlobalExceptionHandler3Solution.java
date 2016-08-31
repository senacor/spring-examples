
package com.exercise.solution;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by amishra on 12.03.2016
 * */

@ControllerAdvice
@Order(4)
public class GlobalExceptionHandler3Solution extends ResponseEntityExceptionHandler{

    protected ResponseEntity<Object> handleExceptionInternal(HttpMediaTypeException ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
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

    @ExceptionHandler({HttpMediaTypeException.class})
    @ResponseBody
    public ResponseEntity<Object> handleException2(HttpServletRequest request, HttpMediaTypeException e) throws Exception{
        System.out.println("Inside Handler      ^   ^   ");
        return this.handleExceptionInternal(e, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public BaseResponse createErrorMessage(HttpMediaTypeException ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message("unerwartete_exception", Severity.ERROR);
        System.out.println("The message is : " +ex.getMessage());
        if(ex.getMessage()== null || ex.getMessage() == "") {
            String msg = ex.getClass().toGenericString().substring(23);
            msg = msg.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
            message.setMessage(msg);
        }
        else
            message.setMessage(ex.getMessage());
        System.out.println(ex.getClass());
        message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }

}



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
 */

