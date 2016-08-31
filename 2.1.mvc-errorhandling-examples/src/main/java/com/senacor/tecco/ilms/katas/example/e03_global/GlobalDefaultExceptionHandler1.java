package com.senacor.tecco.ilms.katas.example.e03_global;

import com.senacor.tecco.ilms.katas.common.response.ErrorMessageComposer;
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
 *
 * This GlobalDefaultExceptionHandler handles NullPointerExceptions
 * In the case of a conflict, this controller has the hightest precedence and it's exception handler will
 * be priorized over the other ones
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
