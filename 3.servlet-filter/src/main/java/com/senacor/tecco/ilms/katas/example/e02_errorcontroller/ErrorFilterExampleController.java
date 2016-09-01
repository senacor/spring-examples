package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michael Menzel on 01.09.2016.
 *
 *
 */
@RestController
public class ErrorFilterExampleController {

    @RequestMapping(value = "/filter/response/error")
    public String createOkResponse1(){
        return "Response: OK";
    }

    @RequestMapping(value = "/filter/response/exception")
    public String createOkResponse2(){
        return "Response: OK";
    }

}
