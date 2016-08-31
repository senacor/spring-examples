package com.example.e03_global;
/*
import com.common.exceptions.ErrorMessageComposer;
import com.common.response.BaseResponse;
import com.example.e02_method.UserNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
*/
/**
 * Created by fsubasi on 17.02.2016.
 * In this example there are two GlobalDefaultExceptionHandlers. They both have an @ExceptionHandler annotated method
 * but the one with the higher precendence will win and handle the exception.
 */
/*
@ControllerAdvice
@Order(10)
public class GlobalDefaultExceptionHandler1 {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        System.out.println("Inside Default ErrorHandler");
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseResponse createErrorMessage(Exception e) {
        return ErrorMessageComposer.throwException(e, "unexpected_exception");
    }
}
*/