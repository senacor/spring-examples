package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsubasi on 17.01.2016.
 *
 * Normally any unhandled exception thrown when processing a web-request causes the server to return
 * an HTTP 500 response.
 * With the @ResponseStatus annotation (which supports all the HTTP status codes defined by the HTTP
 * specification) it's possible to annotate exceptions.
 * When an annotated exception is thrown from a controller method, and not handled elsewhere, it will
 * automatically cause the appropriate HTTP response to be returned with the specified status-code.
 *
 * In this controller we simply throw a BookNotFoundException in order to be able to observe
 * the response code and message we configured in our custom exception by using @ResponseStatus
 */
@Controller
@RequestMapping("/errorHandling/annotatedException")
public class AnnotatedExceptionController {

    @Autowired
    ApplicationContext context;

    @RequestMapping("")
    public String throwBookNotFoundException(){
        throw new BookNotFoundException();
    }

}
