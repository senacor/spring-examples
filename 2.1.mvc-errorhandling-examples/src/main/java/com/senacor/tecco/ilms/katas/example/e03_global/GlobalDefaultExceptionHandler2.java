package com.senacor.tecco.ilms.katas.example.e03_global;
/*
import com.common.response.BaseResponse;
import com.common.response.Message;
import com.common.response.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
*/
/**
 * Created by fsubasi on 17.01.2016
 * Here there are two points. First it shows how ResponseEntityExceptionHandler can be extended and with its
 * handleExceptionInternal method we can get the HttpStatus of some of the standard spring MVC exception.(shown below)
 * The second point is @Order annotation and how it can be used to override behavior of other @ControllerAdvice annotated
 * methods. A helpful stackoverflow question
 * http://stackoverflow.com/questions/19498378/setting-precedence-of-multiple-controlleradvice-exceptionhandlers
 */
/*
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler2 extends ResponseEntityExceptionHandler{

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

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        return this.handleExceptionInternal(e, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public BaseResponse createErrorMessage(Exception ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message("unerwartete_exception", Severity.ERROR);
        System.out.println(ex.getMessage());
        String msg = ex.getClass().toGenericString().substring(23);
        msg = msg.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
        message.setMessage(msg);
        System.out.println(ex.getClass());
        // Convert Exception to String to avoid infinite loop in Jackson Serialization
        message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }
}
*/



