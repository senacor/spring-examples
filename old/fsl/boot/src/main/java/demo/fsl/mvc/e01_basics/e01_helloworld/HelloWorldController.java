package demo.fsl.mvc.e01_basics.e01_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fsubasi on 18.01.2016.
 * Simple Hello World! example
 */
@Controller // indicates the class is a controller, is a @Component e.g. candidate for auto-detection
public class HelloWorldController {

    @RequestMapping(value = "/basics/helloWorld") // maps all HTTP methods by default
    @ResponseBody // convert the returned object to a response body using an HttpMessageConverter
    public String helloWorld(){
        return "Hello World!";
    }
}
