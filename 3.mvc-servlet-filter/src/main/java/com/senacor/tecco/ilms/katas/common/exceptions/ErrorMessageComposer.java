package com.senacor.tecco.ilms.katas.common.exceptions;

import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import com.senacor.tecco.ilms.katas.common.response.Message;
import com.senacor.tecco.ilms.katas.common.response.Severity;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by aaumaitre on 30.08.2016.
 */
public class ErrorMessageComposer {

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
