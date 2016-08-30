package com.common.response;

import com.common.model.User;
import com.common.response.BaseResponse;
import com.common.response.Message;

/**
 * Created by fsubasi on 15.02.2016.
 */
public class UserResponse extends BaseResponse {
    private User user;

    public UserResponse(){
        // no-arg custructor for jackson
    }

    public UserResponse(Message message){
        super(false);
        this.get_messages().add(message);
    }

    public UserResponse(User user){
        super(true); // the operation is successful, i.e. we have the price
        this.setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
