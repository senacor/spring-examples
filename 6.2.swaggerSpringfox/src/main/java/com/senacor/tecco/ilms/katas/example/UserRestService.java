package com.senacor.tecco.ilms.katas.example;

import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import com.senacor.tecco.ilms.katas.common.response.ServiceResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Created by amishra on 17.03.16.
 * Modified for Swagger by asiddiqui
 *
 * This class provides REST interfaces for user related operations
 * In this particular project it acts as a primary example for Swagger documentation
 */
@Controller
@RequestMapping("/user")
@Api(value="user", description="Resources for user service")
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
    @ApiOperation(httpMethod = "GET", value = "Get user by last name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")})
    public List<User> getUserByLastName(@ApiParam(value = "Last name of the required user", required = true)
                                            @RequestParam(value = "lastName", required = true) String lName) {
        return userDB.getUsersByLastName(lName);
    }

    @RequestMapping(value = "/{parameter:\\d+}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Get user by user id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")})
    public User getUserFromId(@ApiParam(value = "Id of the required user", required = true)
                                  @PathVariable("parameter") int uId){
        User user = userDB.getUserFromID(uId);
        return user;
    }

    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "Enter new user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<ServiceResponse> saveUsers(@ApiParam(value = "Json formulated details for the new user",
            required = true)@RequestBody User user){
        User user1 = userDB.saveUser(user);
        ServiceResponse<User> userResponse = new ServiceResponse<>(user1);
        HttpHeaders headers = new HttpHeaders();
        String location = "http://localhost/user/99"; //+ Integer.toString(user1.getUserId());
        URI locationUri = URI.create(location);
        headers.setLocation(locationUri);
        return new ResponseEntity<>(userResponse, headers, HttpStatus.CREATED);
    }

    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(httpMethod = "PUT", value = "Update user", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")})
    public String putUser(@ApiParam(value = "Json formulated user details which needs to be updated",
            required = true) @RequestBody User user) {

        userDB.updateUser(user);
        String response = "The details have been updated for the user with id : " +
                Integer.toString(user.getUserId());
        return response;

    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(httpMethod = "DELETE", value = "Delete user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")})
    public String delete(@ApiParam(value = "Id of the user which needs to be deleted", required = true)
                             @PathVariable("id") int uId) {

        userDB.deleteUser(uId);
        String response = "The user with id " + Integer.toString(uId) + " has been deleted";
        return response;
    }

}
