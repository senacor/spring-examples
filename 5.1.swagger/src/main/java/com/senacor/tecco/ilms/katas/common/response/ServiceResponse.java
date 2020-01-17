package com.senacor.tecco.ilms.katas.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.RepresentationModel;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dr. Michael Menzel, Senacor Technologies AG
 *
 * Base Response for all REST services
 * includes a set of messages that provide response details
 */
public class ServiceResponse<T> extends RepresentationModel {

    private static final long serialVersionUID = 7333730481583143017L;

    /**
     * Optional messages with response details
     */
    private List<Message> messages = new ArrayList<>();

    /**
     * the response result
     */
    private T result;

    /* Constructors */
    public ServiceResponse() {
    }

    public ServiceResponse(@NotNull List<Message> messages) {
        this.messages.addAll(messages);
    }

    public ServiceResponse(@NotNull Message message) {
        this.messages.add(message);
    }

    public ServiceResponse(@NotNull List<Message> messages, @NotNull T result) {
        this(messages);
        this.result = result;
    }

    public ServiceResponse(@NotNull T result) {
        this();
        this.result = result;
    }

    /* Getter and Setter */
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @XmlTransient
    @JsonIgnore
    public boolean isSuccessful() {
        if (messages != null) {
            return !messages.stream().anyMatch(message -> Severity.ERROR.equals(message.getSeverity()));
        }
        return true;
    }
}
