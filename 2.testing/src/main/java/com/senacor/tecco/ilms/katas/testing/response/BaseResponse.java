package com.senacor.tecco.ilms.katas.testing.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 112233445566L;
	
    private Boolean _successful;
    private List<Message> _messages = new ArrayList<>();

    public BaseResponse() {
    }

    public BaseResponse(Boolean _successful) {
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
