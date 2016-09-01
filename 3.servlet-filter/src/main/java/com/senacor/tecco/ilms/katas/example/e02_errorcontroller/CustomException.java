package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mmenzel on 01.09.2016.
 */
public class CustomException extends RuntimeException {
    private HttpStatus responseStatus;

    public CustomException(HttpStatus responseStatus, String message) {
        super(message);
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }
}
