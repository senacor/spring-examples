package demo.fsl.mvc.e02_errorhandling.e03_global;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import demo.fsl.mvc.e02_errorhandling.Error;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 17.01.2016
 * We can customize our error response by returning a ResponseEntity instead of the object itself from
 * an @ExceptionHandler method.
 */
// @ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception e) throws Exception{
        Error error = new Error();
        error.setCode(500);
        error.setMessage("Something went wrong");

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // Do extra operations made possible by ResponseEntity like setting headers...
        HttpHeaders headers = new HttpHeaders();
        headers.set("key", "value");
        return new ResponseEntity<Error>(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<Error> handleNullPointerException(HttpServletRequest request, Exception exc){
        Error error = new Error();
        error.setCode(HttpStatus.I_AM_A_TEAPOT.value());
        error.setMessage("I am a teapot");

        // Do extra operations made possible by ResponseEntity like setting headers...
        HttpHeaders headers = new HttpHeaders();
        headers.set("key", "value");
        return new ResponseEntity<Error>(error, headers, HttpStatus.I_AM_A_TEAPOT);
    }
}
