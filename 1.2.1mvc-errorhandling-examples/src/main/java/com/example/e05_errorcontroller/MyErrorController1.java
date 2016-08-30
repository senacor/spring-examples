/*

package com.example.e05_errorcontroller;

import com.common.BaseResponse;
import com.common.UserResponse;
import com.example.e03_global.GlobalDefaultExceptionHandler1;
import com.example.e04_errorattributes.MyErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;



*/
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
 *//*


*/
/*@Controller
public class MyErrorController1 implements ErrorController {

    private MyErrorAttributes errorAttributes;

    public MyErrorController1(){
        this.errorAttributes = new MyErrorAttributes();
    }

    @Autowired
    GlobalDefaultExceptionHandler1 exceptionHandler;

    @Value("${server.error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${server.error.path:/error}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BaseResponse> error(Exception e) throws Exception{
       // ServletException servletException = (ServletException)request.getAttribute("javax.servlet.error.exception");
        BaseResponse response = exceptionHandler.createErrorMessage(e);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<BaseResponse>(response, headers, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(new UserResponse(), new HttpHeaders(), HttpStatus.CREATED);
        return this.errorAttributes.getErrorAttributes(requestAttributes, false);
    }


}*//*


@Controller
public class MyErrorController1 implements ErrorController {

    private MyErrorAttributes errorAttributes;
    HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public MyErrorController1(){
        this.errorAttributes = new MyErrorAttributes();
    }

    public MyErrorController1(MyErrorAttributes errorAttributes) {
        if (errorAttributes instanceof MyErrorAttributes) {
            this.errorAttributes = (MyErrorAttributes) errorAttributes;
        }
    }
    @Autowired
    GlobalDefaultExceptionHandler1 exceptionHandler;

   @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }


    @RequestMapping(value = "${error.path:/error}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BaseResponse> error(Exception e) throws Exception{
        //ServletException servletException = (ServletException)request.getAttribute("javax.servlet.error.exception");
        System.out.println("Inside Error Controller1");
        BaseResponse response = exceptionHandler.createErrorMessage(e);
        HttpHeaders headers = new HttpHeaders();
        ResponseStatus exResponseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (exResponseStatus != null) {
            returnStatus = exResponseStatus.value();
        }
        return new ResponseEntity<BaseResponse>(response, headers, returnStatus);
    }

    @RequestMapping(value = "${error.path:/error}")
    @ResponseBody
    public ResponseEntity<Object> error(HttpServletRequest request, WebRequest webRequest) {
        System.out.println("Inside Error Controller2");
        return exceptionHandler.getResponseForErrorPage((Exception) getErrorAttributes(request).get(MyErrorAttributes.EXCEPTION_INSTANCE_ATTRIBUTE_NAME), webRequest);
    }

    @RequestMapping(value = "${error.path:/error}", produces = "text/html")
    public ResponseEntity<String> errorHTML(HttpServletRequest request, WebRequest webRequest) {
        System.out.println("Inside Error Controller3");
        ResponseEntity<Object> org = exceptionHandler.getResponseForErrorPage((Exception) getErrorAttributes(request).get(MyErrorAttributes.EXCEPTION_INSTANCE_ATTRIBUTE_NAME), webRequest);
        String html = "<html><head><title>FSL Error Page</title></html>";
        return new ResponseEntity<String>(html, org.getStatusCode());
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, false);
    }
}*/
