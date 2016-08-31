package com.senacor.tecco.ilms.katas.example.e05_errorcontroller;

import com.senacor.tecco.ilms.katas.common.MyError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
/*
@Controller
public class MyErrorController implements ErrorController {
    @Value("${server.error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${server.error.path:/error}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<MyError> error(HttpServletResponse response){
        System.out.println("Inside Error Controller 1");

        MyError error = getErrorFromResponse(response);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyError>(error, headers, HttpStatus.valueOf(error.getCode()));
    }


    private MyError getErrorFromResponse(HttpServletResponse response){

        System.out.println("Inside Error Controller Response");

        int errorCode = response.getStatus();

        MyError error = new MyError();
        error.setCode(errorCode);
        error.setMessage(HttpStatus.valueOf(errorCode).getReasonPhrase());
        return error;
    }
*/
