package com.senacor.tecco.ilms.katas.example.e02_method;

import com.senacor.tecco.ilms.katas.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by fsubasi on 05.01.2016.
 *
 * Exception Example 2: Intercepting Exception in Controllers
 *
 * To handle exceptions thrown by request handling (@RequestMapping) methods,
 * methods annotated with @ExceptionHandler can be added to any controller.
 *
 * These (@ExceptionHandler) methods take precedence over other exception handlers (for example,
 * over exception handling performed in controller advices, see Example 3)
 *
 * In this example we throw an IllegalArgumentException and observe that it is intercepted
 * by the @ExceptionHandler annotated method in this controller.
 */

@RestController
@RequestMapping("/errorHandling")
public class ErrorHandlingMethodController {

    @RequestMapping(value = "errorhandler")
    @ResponseBody
    public String throwException(){
        throw new IllegalArgumentException("¯\\_(ツ)_/¯");
    }

    // @ExceptionHandler declares an exception handler for the specified
    // exception types. Only exceptions thrown in the same controller
    // will be intercepted by the error handler method.
    //
    // If the annotation value is not used, then the exception types
    // listed as method arguments are used
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<Object> errorHandler(IllegalArgumentException e){
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorResponse createErrorMessage(Exception e) {
        return new ErrorResponse("illegal_argument_exception", e.getMessage());
    }
}
