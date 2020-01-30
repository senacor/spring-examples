package com.senacor.tecco.ilms.katas.example.e01_filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 /**
 * Created by Dr. Michael Menzel, Senacor Technologies AG, 01.09.2016.
 *
 * This examples illustrates the usage of servlet filters. See MyFilter.java
 * for filter definition and registration. Requests to /filter/response/*
 * are intercepted by MyFilter that logs the request URL and the response status
 */
@RestController
public class FilterExampleController {

    @RequestMapping(value = "/filter/response/ok")
    public String createOkResponse(){
        return "Response: OK";
    }

    @RequestMapping(value = "/filter/response/created")
    public ResponseEntity<String> createCreatedResponse(){
        return new ResponseEntity<>("Response: Created", HttpStatus.CREATED);
    }
}
