package com.exercise.solution;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by amishra on 10.03.2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "User not found.")
public class UserNotFoundSolutionException extends RuntimeException {

    private static final long serialVersionUID = -6502596312985405760L;
    public UserNotFoundSolutionException(String message) {
        super(message);
    }

}
