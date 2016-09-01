package com.senacor.tecco.ilms.katas.example.e01_filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael Menzel on 01.09.2016.
 *
 *
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
