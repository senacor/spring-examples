package com.exercise.solution;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by amishra on 12.03.2016.
 */
@ControllerAdvice
@Order(20)
public class GlobalExceptionHandler1Solution {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        System.out.println("Inside global handler");
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

   /* public BaseResponse createErrorMessage(Exception ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);

        Message message = new Message("unexpected_exception", Severity.ERROR);
        message.setMessage(ex.getMessage());
        message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }*/

    public BaseResponse createErrorMessage(Exception ex) {
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
