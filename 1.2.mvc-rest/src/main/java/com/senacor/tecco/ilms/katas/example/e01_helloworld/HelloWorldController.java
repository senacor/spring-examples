package com.senacor.tecco.ilms.katas.example.e01_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fsubasi on 18.01.2016.
 * Simple Hello World! example
 * returns a string to /basics/helloWorldRest requests
 *
 * Spring MVC controller with request mapping methods return a string that
 * identifies the view used by Spring MVC to render the response
 * To indicate that the returned value represents the response that should be passed
 * to the client, the annotation @ResponseBody is used
 */
@Controller // indicates the class is a controller, is a @Component i.e. candidate for auto-detection
public class HelloWorldController {

    // maps /basics/helloWorld for all HTTP methods to the helloWorld-Method
    @RequestMapping(value = "/basics/helloWorld")
    // @ResponseBody on a Controller method indicates to Spring that
    // the return value of the method is serialized directly to the body
    // of the HTTP Response using an HttpMessageConverter
    @ResponseBody
    public String helloWorld(){
        return "Hello world!";
    }
}
