package com.exercise;

import com.common.model.User;
import com.common.response.BaseResponse;
import com.common.response.UserResponse;
import com.common.service.UserService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by amishra on 17.03.16.
 */
@Controller
@RequestMapping("/user")

public class UserServiceExerciseSolution {

	UserService userService;
	
    public UserServiceExerciseSolution() {
    	userService = new UserService();
    }

    /**********************************************************************
     *
     * GET
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getUserByLastName(@RequestParam(value = "lastName", required = true) String lName) {
        List<User> userList = userService.getUsersByLastName(lName);
        return userList;
    }

    @RequestMapping(value = "/{parameter:\\d+}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserFromId(@PathVariable("parameter") int uId){
        User user = userService.getUserFromID(uId);
        return user;
    }

    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String consumeUser() {

        return "Request Successful";
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveUsers(@RequestBody User user){
        User user1 = userService.saveUser(user);
        UserResponse userResponse = new UserResponse(user1);
        HttpHeaders headers = new HttpHeaders();
        String location = "http://localhost/user/99"; //+ Integer.toString(user1.getUserId());
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

    	userService.updateUser(user);
        String response = "The details have been updated for the user with id : " + Integer.toString(user.getUserId());
        return response;

    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") int uId) {

    	userService.deleteUser(uId);
        String response = "The user with id " + Integer.toString(uId) + " has been deleted";
        return response;
    }

}
