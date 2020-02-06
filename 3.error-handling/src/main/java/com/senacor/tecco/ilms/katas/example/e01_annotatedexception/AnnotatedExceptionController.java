package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import com.senacor.tecco.ilms.katas.common.service.ExceptionThrowingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

/**
 * Exception Example 1: Default Exception Handling with spring
 *
 * When processing web-requests, spring intercepts unhandled exception with its
 * DefaultExceptionResolver. The Resolver sends an servlet error response with an
 * HTTP error code that is determined based on the type of the exception.
 * Specific exceptions are transformed to predefined HTTP error codes,
 * while runtime exceptions and custom runtime exceptions are transformed to an
 * HTTP 500 response code.
 *
 * The servlet container redirects to the error page that is handled by the predefined
 * Spring error controller. The error controller generates an error object and sets the
 * provided http response code. The representation of the error object depends on the
 * content negotiation. In case of HTTP, the white label error page will be generated.
 *
 * With the @ResponseStatus annotation (which supports all the HTTP status codes defined by the HTTP
 * specification) it's possible to annotate exceptions to enable an automated mapping
 * of exceptions to HTTP status codes and reasons.
 *
 * In this controller, we simply throw a BookNotFoundException in order to be able to observe
 * the response code and message we configured in our custom exception by using @ResponseStatus
 */
@RestController
@RequestMapping(value = "/errorhandling")
public class AnnotatedExceptionController {

    private final ExceptionThrowingService service;

    public AnnotatedExceptionController(ExceptionThrowingService service) {
        this.service = service;
    }

    /**
     * All unhandled exceptions will result in a {@link NestedServletException} which will be mapped to Http Status Code
     * 500 - Internal Server Error in the actual application
     */
    @GetMapping("/plainexception")
    public String throwRuntimeException(){
        service.throwException(new RuntimeException("Das ist eine Exception"));

        return "Successfully processed request without Exception";
    }

    /**
     * Custom error response status using annotated exception
     */
    @GetMapping("annotatedexception")
    public String throwBookNotFoundException(){
        service.throwException(new BookNotFoundException());

        return "Successfully processed request without Exception";
    }

}
