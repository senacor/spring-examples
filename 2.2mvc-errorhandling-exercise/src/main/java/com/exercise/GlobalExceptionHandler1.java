
package com.exercise;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.Exception;


/**
 * Created by amishra on 12.03.2016.
 * -------------------------------------------------------------------------------------------
 * First Class to define an Exception Handler .
 * -------------------------------------------------------------------------------------------
 * 1. Use Controller Advice to give this particular class a lower priority than the ones in example.
 * 2. Use proper annotations for the "handleException" method so that it can handle exceptions.
 */


public class GlobalExceptionHandler1 {


    public ResponseEntity<Object> handleException(HttpServletRequest request, java.lang.Exception e) throws java.lang.Exception {
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
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

