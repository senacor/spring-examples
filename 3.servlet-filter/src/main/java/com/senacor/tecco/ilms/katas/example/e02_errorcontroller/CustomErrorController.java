package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 19.01.2016.
 *
 * In order to intercept internal requests to the error page,
 * the marker interface 'ErrorController' must be implemented.
 * If no custom implementation of ErrorController is provided,
 * Spring Boot will register the Spring Bean
 * BasicErrorController as default implementation automatically
 *
 * In general, exception raised in spring controllers should be
 * caught and handled within a controller advice.
 *
 * However, if errors or exceptions are raised in servlet filters,
 * a redirect to the error controller will occur.
 *
 * This simple error controller extracts the message and respond status
 * from the request attributes
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

        //extract information from exception if available
        if(exception instanceof CustomException) {
            CustomException customException = (CustomException) exception;
            message = customException.getMessage();
            statusCode = customException.getResponseStatus();
        //if no exception occurred, try to extract message and
        //status from servlet error attributes
        } else {
            message = (String) request.getAttribute("javax.servlet.error.message");
            statusCode = HttpStatus.valueOf((Integer) request.getAttribute("javax.servlet.error.status_code"));
        }

        System.out.println(message);
        System.out.println(statusCode);

        return new ResponseEntity<>("Error handled by error handler: " + message, statusCode);
    }


}
