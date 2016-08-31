package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.exceptions.ErrorMessageComposer;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import org.springframework.core.Ordered;
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
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler1 {

    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseResponse createErrorMessage(Exception e) {
        return ErrorMessageComposer.messageComposer(e, "exception_handled_by_global_handler_1");
    }
}
