package com.senacor.tecco.ilms.katas.common.response;

import com.senacor.tecco.ilms.katas.common.model.User;

/**
 * Created by fsubasi on 15.02.2016.
 */
public class UserResponse extends BaseResponse {
    
	private static final long serialVersionUID = 112233445577L;
	
	private User user;

    public UserResponse(){
        // no-argument constructor for jackson
    }

    public UserResponse(Message message){
        super(false);
        this.get_messages().add(message);
    }

    public UserResponse(User user){
        super(true); // the operation is successful, i.e. we have the user
        this.setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
