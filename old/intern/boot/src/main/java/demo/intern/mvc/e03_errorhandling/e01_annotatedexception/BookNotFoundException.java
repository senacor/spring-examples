package demo.intern.mvc.e03_errorhandling.e01_annotatedexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fsubasi on 12.01.2016.
 * Custom exceptions can be annotated with @ResponseStatus to respond
 * with the specified HTTP response status and message
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Book not found.") // 404
public class BookNotFoundException extends RuntimeException {

}
