package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fsubasi on 19.01.2016.
 * In order to intercept internal requests to error page, marker interface 'ErrorController' must be implemented,
 * Spring Boot automatically registers the BasicErrorController as a Spring Bean
 * when there is no custom implementation of ErrorController
 *
 * This is also used in common-fsl-service to catch errors not caught by
 * GlobalExceptionHandler with @ControllerAdvice
 *
 * COMMENTED OUT TO STOP INTERFERING IN OTHER PARTS OF THE APPLICATION
 */

@RestController
public class CustomErrorController implements ErrorController {
    @Value("${server.error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${server.error.path:/error}", produces = "application/json")
    public ResponseEntity<String> error(HttpServletRequest request){
        System.out.println("Inside Error Controller");

        String message;
        HttpStatus statusCode;

        //get Exception
        Object exception = request.getAttribute("javax.servlet.error.exception");

        if(exception instanceof CustomException) {
            CustomException customException = (CustomException) exception;
            message = customException.getMessage();
            statusCode = customException.getResponseStatus();
        } else {
            message = (String) request.getAttribute("javax.servlet.error.message");
            statusCode = HttpStatus.valueOf((Integer) request.getAttribute("javax.servlet.error.status_code"));
        }

        System.out.println(message);
        System.out.println(statusCode);

        return new ResponseEntity<String>("Error handled by error handler: " + message, statusCode);
    }


}
