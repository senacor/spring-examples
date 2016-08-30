package com.common.response;

/**
 * Created by fsubasi on 15.02.2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 Die Basisklasse für alle FSL Messagenachrichten
 */
public class Message {
    private String code;
    private Severity severity;
    private String message;
    @JsonIgnoreProperties("mostSpecificCause")
    private Object details;
    private String detailsType;
    private String timestamp;

    public Message(String code, Severity severity) {
        if (!code.matches("[\\p{javaLowerCase}_\\-0-9]+")) throw new IllegalArgumentException("Der Code darf nur aus Kleinbuchstaben, Ziffern und den Zeichen - und _ bestehen. Der Code '"+code+"' verletzt diese Regel.");
        this.code = code;
        this.severity = severity;
        this.timestamp = ""+new Date().getTime();
        this.detailsType = "null";
    }

    public String getCode() {
        return code;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
        this.detailsType = details.getClass().getName();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDetailsType() {
        return detailsType;
    }
}
