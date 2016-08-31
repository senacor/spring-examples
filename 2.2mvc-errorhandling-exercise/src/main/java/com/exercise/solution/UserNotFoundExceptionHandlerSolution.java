
package com.exercise.solution;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import com.exercise.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;


/**
 * Created by amishra on 12.03.2016
 */

@ControllerAdvice
@Order(5)
public class UserNotFoundExceptionHandlerSolution {

    protected ResponseEntity<Object> handleExceptionInternal(UserNotFoundSolutionException ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
        headers.set("key", "value");
        HttpStatus returnStatus = status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        BaseResponse errorMessage = createErrorMessage(ex);

        ResponseStatus exResponseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (exResponseStatus != null) {
            returnStatus = exResponseStatus.value();
        }
        return new ResponseEntity<>(errorMessage, headers, returnStatus);

    }

    @ExceptionHandler({UserNotFoundSolutionException.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, UserNotFoundSolutionException e) throws Exception{
        System.out.println("Inside UserNotFound Exception Handler");
        return this.handleExceptionInternal(e, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }


    public BaseResponse createErrorMessage(Exception ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message("unexpected_exception", Severity.ERROR);
        message.setMessage(ex.getMessage());
        message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }
}


