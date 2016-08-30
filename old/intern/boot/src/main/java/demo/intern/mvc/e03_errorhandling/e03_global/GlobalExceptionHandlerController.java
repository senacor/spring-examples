package demo.intern.mvc.e03_errorhandling.e03_global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsubasi on 17.01.2016
 * In this controller, we throw a RuntimeException and a NullPointerException.
 * Here we observe that different exceptions are being intercepted by their corresponding @ExceptionHandler's
 * in our global exception handler, where error responses can be customized for exception type in question.
 */
@Controller
@RequestMapping("/errorHandling/global")
public class GlobalExceptionHandlerController {

    @RequestMapping("throwException")
    public String throwException(){
        throw new RuntimeException("Something went wrong");
    }

    @RequestMapping("throwNPE")
    public String throwNPE(){
        throw new NullPointerException();
    }
}
