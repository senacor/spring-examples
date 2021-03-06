package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exceptions can be annotated with @ResponseStatus to respond
 * with the specified HTTP response status and message
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "The book was not found")
public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1209203L;
}
