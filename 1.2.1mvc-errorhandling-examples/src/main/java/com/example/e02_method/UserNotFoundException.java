package com.example.e02_method;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by amishra on 10.03.2016.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "User not found.")
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6502596312985405760L;
    public UserNotFoundException(String message) {
        super(message);
    }

}
