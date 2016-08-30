package com.common.exceptions;

import com.common.response.BaseResponse;
import com.common.response.Message;
import com.common.response.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aaumaitre on 30.08.2016.
 */
public class ExceptionThrower {

    public static BaseResponse throwException(Exception e, String textOfMessage){
        BaseResponse output = new BaseResponse();
        output.set_successful(false);
        Message message = new Message(textOfMessage, Severity.ERROR);
        message.setMessage(e.getMessage());
        // Convert Exception to String to avoid infinite loop in Jackson Serialization
        message.setDetails(ExceptionUtils.getStackTrace(e));
        output.get_messages().add(message);
        return output;
    }
}
