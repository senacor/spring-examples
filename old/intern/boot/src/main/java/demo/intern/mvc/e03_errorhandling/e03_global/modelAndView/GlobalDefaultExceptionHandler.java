package demo.intern.mvc.e03_errorhandling.e03_global.modelAndView;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 12.01.2016.
 */
/**
 * @ControllerAdvice is a @Component that advises all controllers in a web application.
 * Here we can declare @ExceptionHandler methods which will intercept exceptions
 * thrown from any controller.
 */
// @ControllerAdvice
public class GlobalDefaultExceptionHandler{
    public static final String DEFAULT_ERROR_VIEW = "error";

    // Here we use a ViewResolver, so we avoid @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception{
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - see BookNotFoundException class
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getClass().getName());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ModelAndView handleNullPointerException(HttpServletRequest request, Exception exc){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exc);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("nullPointerView");
        return modelAndView;
    }
}
