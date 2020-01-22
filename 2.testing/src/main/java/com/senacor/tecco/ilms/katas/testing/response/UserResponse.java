package com.senacor.tecco.ilms.katas.testing.response;

import com.senacor.tecco.ilms.katas.testing.model.User;

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
