package com.example.e01_annotatedexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

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
/*
    @RequestMapping("/queryParam")
    @ResponseBody
    public String queryParam(@RequestParam("name")String name) throws Exception{
        if(true)
            throw new HttpMediaTypeNotAcceptableException("BUBU");
        HandlerExceptionResolverComposite composite = (HandlerExceptionResolverComposite)context.getBean("handlerExceptionResolver");
        return composite.getExceptionResolvers().stream().map(handlerExceptionResolver -> handlerExceptionResolver.toString())
                .reduce("", (a,b)->String.join(",", a, b));
    }*/
}
