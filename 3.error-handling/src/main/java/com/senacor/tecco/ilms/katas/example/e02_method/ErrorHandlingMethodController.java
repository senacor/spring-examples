package com.senacor.tecco.ilms.katas.example.e02_method;

import com.senacor.tecco.ilms.katas.common.response.ErrorResponse;
import com.senacor.tecco.ilms.katas.common.service.ExceptionThrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by fsubasi on 05.01.2016.
 * <p>
 * Exception Example 2: Intercepting Exception in Controllers
 * <p>
 * To handle exceptions thrown by request handling (@RequestMapping) methods,
 * methods annotated with @ExceptionHandler can be added to any controller.
 * <p>
 * These (@ExceptionHandler) methods take precedence over other exception handlers (for example,
 * over exception handling performed in controller advices, see Example 3)
 * <p>
 * In this example we throw an IllegalArgumentException and observe that it is intercepted
 * by the @ExceptionHandler annotated method in this controller.
 */

@RestController
@RequestMapping("/errorhandling")
public class ErrorHandlingMethodController {

    private final ExceptionThrowingService service;

    public ErrorHandlingMethodController(ExceptionThrowingService service) {
        this.service = service;
    }

    @GetMapping("/errorhandler")
    public String throwException() {
        service.throwException(new IllegalArgumentException("¯\\_(ツ)_/¯"));

        return "Successfully processed request without Exception";
    }

    /**
     * @ExceptionHandler declares an exception handler for the specified
     * exception types. Only exceptions thrown in the same controller
     * will be intercepted by the error handler method.
     * <p>
     * If the annotation value is not used, then the exception types
     * listed as method arguments are used
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandler(IllegalArgumentException e) {
        return createErrorMessage(e);
    }

    public ErrorResponse createErrorMessage(Exception e) {
        return new ErrorResponse("illegal_argument_exception", e.getMessage());
    }
}
