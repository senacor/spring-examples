package com.senacor.tecco.ilms.katas.example.e03_global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsubasi on 17.01.2016
 *
 * A controller advice allows to use the same exception handling techniques
 * as in controllers (Example 2), but applies them to all controller requests,
 * not just to an individual controller. They behave like annotation driven
 * interceptors.
 *
 * Any class annotated with @ControllerAdvice becomes a controller-advice.
 * The methods used to handle the exceptions are annotated with @ExceptionHandler.
 *
 * It's possible to define multiple global exception handlers that
 * handle different kinds of exceptions. If one exception can be handled by
 * different global exception handlers, the method from the
 * global exception handler with highest priority will be executed
 *
 * In this controller, we throw a NumberFormatException and a NullPointerException.
 * Here we observe that different exceptions are being intercepted by
 * their corresponding @ExceptionHandler's
 */

@Controller
@RequestMapping("/errorHandling/global")
public class GlobalExceptionHandlerController {

    @RequestMapping("throwNFE")
    public String messageComposer(){
        throw new NumberFormatException();
    }

    @RequestMapping("throwNPE")
    public String throwNPE(){
        throw new NullPointerException();
    }
}
