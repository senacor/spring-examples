package com.senacor.tecco.ilms.katas.example.e02_method;

import com.senacor.tecco.ilms.katas.common.response.ErrorMessageComposer;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fsubasi on 05.01.2016.
 *
 * It's possible to add extra (@ExceptionHandler) methods to any controller to specifically
 * handle exceptions thrown by request handling (@RequestMapping) methods in the same controller
 * These (@ExceptionHandler) methods take precedence over other exception handlers (for example,
 * over global exception controllers)
 *
 * In this example we throw an IllegalArgumentException and observe that it is intercepted
 * by the @ExceptionHandler annotated method in this controller.
 */

@Controller
@RequestMapping("/errorHandling/method")
public class ErrorHandlingMethodExample {

    @RequestMapping(value = "messageComposer")
    @ResponseBody
    public String throwException(){
        throw new IllegalArgumentException("¯\\_(ツ)_/¯");
    }

    // @ExceptionHandler declares an exception handler for the specified
    // exception types. Only exceptions thrown in the same controller
    // will be intercepted by the error handler method.
    @ExceptionHandler(IllegalArgumentException.class)// NOTE TO SELF: If the annotation value is not used,
    // then the exception types listed as method arguments are used
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public BaseResponse throwError(IllegalArgumentException e){
        return ErrorMessageComposer.messageComposer(e, "illegal_argument_exception");
    }
}
