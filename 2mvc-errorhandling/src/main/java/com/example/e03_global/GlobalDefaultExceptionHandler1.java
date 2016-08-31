package com.example.e03_global;

import com.common.BaseResponse;
import com.common.Message;
import com.common.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fsubasi on 17.02.2016.
 * In this example there are two GlobalDefaultExceptionHandlers. They both have an @ExceptionHandler annotated method
 * but the one with the higher precendence will win and handle the exception.
 */
@ControllerAdvice
@Order(10)
public class GlobalDefaultExceptionHandler1 extends ResponseEntityExceptionHandler implements Ordered{

    private  final Log LOG = LogFactory.getLog( GlobalDefaultExceptionHandler1.class );
    //LogFactory.getLog(GlobalControllerExceptionHandler.class );

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+100;
    }

    private static GlobalDefaultExceptionHandler1 instanz = new GlobalDefaultExceptionHandler1();

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) throws Exception{
        return new ResponseEntity<Object>(createErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseResponse createErrorMessage(Exception ex) {
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message("unerwartete_exception", Severity.ERROR);
        System.out.println(ex.getMessage());
        String msg = ex.getClass().toGenericString().substring(23);
        msg = msg.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
        message.setMessage(msg);
        System.out.println(ex.getClass());
        message.setDetails(ExceptionUtils.getStackTrace(ex));
        output.get_messages().add(message);
        return output;
    }

    public static ResponseEntity<Object> getResponseForErrorPage(Exception ex, WebRequest request) {
        return instanz.handleException(ex,request);
    }

}
