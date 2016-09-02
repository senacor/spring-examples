package com.common.response;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fsubasi on 15.02.2016.
 */
public class BaseResponse implements Serializable {

    private Boolean _successful;
    private List<Message> _messages = new ArrayList<>();

    public BaseResponse() {
    }

    public BaseResponse(Boolean _successful) {
        this._successful = _successful;
    }

    public BaseResponse(Boolean _successful, @NotNull List<Message> _messages) {
        this._messages.addAll(_messages);
        this._successful = _successful;
    }

    public Boolean get_successful() {
        return _successful;
    }

    public void set_successful(Boolean _successful) {
        this._successful = _successful;
    }

    public List<Message> get_messages() {
        return _messages;
    }
}
