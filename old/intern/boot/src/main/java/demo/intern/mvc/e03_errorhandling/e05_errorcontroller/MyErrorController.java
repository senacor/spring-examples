package demo.intern.mvc.e03_errorhandling.e05_errorcontroller;

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
 */ /*
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
    public ResponseEntity<Error> error(HttpServletResponse response){
        Error error = getErrorFromResponse(response);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Error>(error, headers, HttpStatus.valueOf(error.getCode()));
    }

    @RequestMapping(value = "${server.error.path:/error}", produces = "text/html")
    public String errorHTML(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response){
        Error error = getErrorFromResponse(response);
        model.put("exception", error);
        model.put("url", request.getRequestURL());
        ServletException servletException = (ServletException)request.getAttribute("javax.servlet.error.exception");
        if(servletException != null && (servletException.getRootCause() instanceof NullPointerException)){
            return "nullPointerView";
        }
        return "error";
    }

    private Error getErrorFromResponse(HttpServletResponse response){
        int errorCode = response.getStatus();

        Error error = new Error();
        error.setCode(errorCode);
        error.setMessage(HttpStatus.valueOf(errorCode).getReasonPhrase());
        return error;
    }

}
*/