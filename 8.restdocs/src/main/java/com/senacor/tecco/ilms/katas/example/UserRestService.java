package com.senacor.tecco.ilms.katas.example;

import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import com.senacor.tecco.ilms.katas.common.response.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by amishra on 17.03.16.
 *
 * This class provides REST interfaces for user related operations
 */
@Controller
@RequestMapping("/user")

public class UserRestService {

    UserService userDB =new UserService();

    public UserRestService() {
    }

    /**********************************************************************
     *
     * GET
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getUserByLastName(@RequestParam(value = "lastName", required = true) String lName) {
        List<User> userList = userDB.getUsersByLastName(lName);
        return userList;
    }

    @RequestMapping(value = "/{parameter:\\d+}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserFromId(@PathVariable("parameter") int uId){
        User user = userDB.getUserFromID(uId);
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
        User user1 = userDB.saveUser(user);
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

        userDB.updateUser(user);
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

        userDB.deleteUser(uId);
        String response = "The user with id " + Integer.toString(uId) + " has been deleted";
        return response;
    }

}
