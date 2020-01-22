package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsubasi on 17.01.2016.
 *
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
@Controller
@RequestMapping("/errorHandling")
public class AnnotatedExceptionController {

    @Autowired
    ApplicationContext context;

    @RequestMapping("plainException")
    public String throRuntimeException(){
        throw new RuntimeException("Das ist eine Exception");
    }

    @RequestMapping("annotatedException")
    public String throwBookNotFoundException(){
        throw new BookNotFoundException();
    }

}
