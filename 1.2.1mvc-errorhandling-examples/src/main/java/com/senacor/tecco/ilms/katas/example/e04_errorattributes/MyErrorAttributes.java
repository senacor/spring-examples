package com.senacor.tecco.ilms.katas.example.e04_errorattributes;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * Created by fsubasi on 19.01.2016.
 */

/**
 * ErrorAttributes interface provides access to error attributes. It has two methods
 * getError and getErrorAttributes. Here we extend DefaultErrorAttributes whose
 * getError method returns the java.lang.Throwable that was thrown and
 * getErrorAttributes returns a map of error attributes which can be used to display
 * the error to the user or log the error. Attributes can be added/removed.
 * A bean of type MyErrorAttributes is defined in ErrorHandlingConfig
 */
public class MyErrorAttributes extends DefaultErrorAttributes {

    public static final String EXCEPTION_INSTANCE_ATTRIBUTE_NAME = "exceptionInstance";
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        errorAttributes.put("node", "node1");
        return errorAttributes;
    }
}
