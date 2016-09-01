package com.senacor.tecco.ilms.katas.common.response;

import java.io.Serializable;

/**
 * Created by fsubasi on 15.02.2016.
 */
public class ErrorResponse implements Serializable {

    private String code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String code, String exceptionMessage) {
        if (!code.matches("[\\p{javaLowerCase}_\\-0-9]+")) throw new IllegalArgumentException("Der Code darf nur aus Kleinbuchstaben, Ziffern und den Zeichen - und _ bestehen. Der Code '"+code+"' verletzt diese Regel.");
        this.code = code;
        this.message = exceptionMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
