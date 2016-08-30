package demo.intern.mvc.e01_basics.e01_helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 24.01.2016.
 * 'Hello World!' with @RestController
 */
@RestController // similar to @Controller, but implies all methods return @ResponseBody
public class HelloWorldRestController {

    @RequestMapping("/basics/helloWorldRest")
    public String helloWorldRest(){
        return "Hello World!";
    }
}
