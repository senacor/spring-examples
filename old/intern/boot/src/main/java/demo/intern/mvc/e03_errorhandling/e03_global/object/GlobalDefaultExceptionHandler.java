package demo.intern.mvc.e03_errorhandling.e03_global.object;

import demo.intern.mvc.e03_errorhandling.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 17.01.2016.
 * In this ControllerAdvice example, we return a @ResponseBody object instead of a view in the previous example.
 * The response will be created by an HttpMessageConverter
 */
// @ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public demo.intern.mvc.e03_errorhandling.Error handleException(HttpServletRequest request, Exception e) throws Exception{
        Error error = new Error();
        error.setCode(500);
        error.setMessage("Something went wrong");
        return error;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    public Error handleNullPointerException(HttpServletRequest request, Exception exc){
        Error error = new Error();
        error.setCode(HttpStatus.I_AM_A_TEAPOT.value());
        error.setMessage("I am a teapot");
        return error;
    }
}
