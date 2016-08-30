package demo.fsl.mvc.e02_errorhandling.e02_method;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import demo.fsl.mvc.e02_errorhandling.Error;

/**
 * Created by fsubasi on 05.01.2016.
 * In this example we throw an IllegalArgumentException and observe that it is intercepted
 * by the @ExceptionHandler annotated method in this controller.
 */
@Controller
@RequestMapping("/errorHandling/method")
public class ErrorHandlingMethodController {

    @RequestMapping(value = "throwException")
    @ResponseBody
    public String throwException(){
        throw new IllegalArgumentException();
    }


    // @ExceptionHandler declares an exception handler for the specified
    // exception types. Only exceptions thrown in the same controller
    // will be intercepted by the error handler method.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    @ResponseBody
    public Error throwError(){
        Error error = new Error();
        error.setCode(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value());
        error.setMessage("Http version not supported");
        return error;
    }
}
