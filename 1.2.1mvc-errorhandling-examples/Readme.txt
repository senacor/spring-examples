This project deals with the exception handling in Spring MVC.
It gives an overview about the different types of exception and how they are handled in Spring.

The example codes are in the path 1.2mvc-errorhandling > src > main > java > com > example.
The exercises are in the path 1.2mvc-errorhandling > src > main > java > com > exercise.

To run the project, we need to use the maven projects tool window.
Then, we have to go to the path mvc-basics > Plugins > spring-boot and then double click on spring-boot:run

/**
 * SPRING MVC EXCEPTIONS
 * ===================================================================
 * BindException                           400 - Bad Request
 * ConversionNotSupportedException         500 - Internal Server Error
 * HttpMediaTypeNotAcceptableException     406 - Not Acceptable
 * HttpMediaTypeNotSupportedException      415 - Unsupported Media Type
 * HttpMessageNotReadableException         400 - Bad Request
 * HttpMessageNotWritableException         500 - Internal Server Error
 * HttpRequestMethodNotSupportedException  405 - Method Not Allowed
 * MethodArgumentNotValidException         400 - Bad Request
 * MissingServletRequestParameterException 400 - Bad Request
 * MissingServletRequestPartException      400 - Bad Request
 * NoSuchRequestHandlingMethodException    404 - Not Found
 * TypeMismatchException                   400 - Bad Request
 */
