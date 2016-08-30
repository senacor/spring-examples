package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.exceptions.UserNotFoundException;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import com.senacor.tecco.ilms.katas.common.response.Message;
import com.senacor.tecco.ilms.katas.common.response.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



/**
 * Created by fsubasi on 17.02.2016.
 * In this example there are two GlobalDefaultExceptionHandlers. They both have an @ExceptionHandler annotated method
 * but the one with the higher precendence will win and handle the exception.
 */



@ControllerAdvice
@Order(10)
public class GlobalDefaultExceptionHandler1 {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        System.out.println("Inside Default ErrorHandler");
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



