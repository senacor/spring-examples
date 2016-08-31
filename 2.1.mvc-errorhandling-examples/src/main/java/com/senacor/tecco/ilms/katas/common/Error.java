package com.senacor.tecco.ilms.katas.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fsubasi on 08.01.2016.
 */
// In order to be able to return data in XML format, we add this annotation to classes
// whose object will be marshalled.
@XmlRootElement
public class Error {
    private int code;
    private String message;
    public Error(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Error(){

    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}