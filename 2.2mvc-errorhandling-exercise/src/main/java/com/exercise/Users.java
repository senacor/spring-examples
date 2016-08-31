package com.exercise;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by amishra on 10.03.16.
 * -------------------------------------------------------------------------------------------
 * Class to Raise UserNotFound Exception.
 * -------------------------------------------------------------------------------------------
 * Hit the URL corresponding to this class to raise the custom exception "UserNotFoundException" when user is not founf.
 */

@Controller
@RequestMapping("/users")
public class Users {

    UserInMemoryDB userB=new UserInMemoryDB();
    public Users() {
        userB.initialiseUsers();
    }

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@RequestParam(value = "id", required = true) String id) {

        User user = getUserFromID(Integer.parseInt(id));

        return user;
    }

    public User getUserFromID(int uId)
    {
        User user1 = userB.getUserFromID(uId);
        return user1;
    }

    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveUsers(@RequestBody User user){
        //System.out.println("inside Controller");
        User user1 = userB.saveUsers(user);
        UserResponse userResponse = new UserResponse(user1);
        HttpHeaders headers = new HttpHeaders();
        String location = "http://localhost/user/"+ Integer.toString(user1.getUserId());
        URI locationUri = URI.create(location);
        headers.setLocation(locationUri);
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(userResponse, headers, HttpStatus.CREATED);
        return responseEntity;
    }


    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public String putUser(@RequestBody User user) {

        userB.updateUser(user);
        String response = "The details have been updated for the user with id : " + Integer.toString(user.getUserId());
        return response;

    }



}

