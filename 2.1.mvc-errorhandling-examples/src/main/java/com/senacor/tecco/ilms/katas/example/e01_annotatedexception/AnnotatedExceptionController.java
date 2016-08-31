package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import com.senacor.tecco.ilms.katas.example.e01_annotatedexception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsubasi on 17.01.2016.
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
