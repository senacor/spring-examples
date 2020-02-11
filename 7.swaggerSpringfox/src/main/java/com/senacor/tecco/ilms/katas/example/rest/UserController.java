package com.senacor.tecco.ilms.katas.example.rest;

import com.senacor.tecco.ilms.katas.example.model.User;
import com.senacor.tecco.ilms.katas.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.LOCATION;


/**
 * This class provides REST interfaces for user related operations
 * In this particular project it acts as a primary example for Swagger documentation
 */
@RestController
@RequestMapping("/user")
@Api(value = "user", description = "Resources for user service")
public class UserController {

    UserService userDB = new UserService();

    /**********************************************************************
     *
     * GET
     *
     *********************************************************************/

    @GetMapping
    @ApiOperation(httpMethod = "GET", value = "Get user by last name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public List<User> getUserByLastName(@ApiParam(value = "Last name of the required user", required = true)
                                        @RequestParam(value = "lastName") String lName) {
        return userDB.getUsersByLastName(lName);
    }

    @GetMapping(value = "/{parameter:\\d+}")
    @ApiOperation(httpMethod = "GET", value = "Get user by user id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public User getUserFromId(@ApiParam(value = "Id of the required user", required = true)
                              @PathVariable("parameter") int uId) {
        return userDB.getUserFromID(uId);
    }

    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Enter new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUsers(
            @ApiParam(value = "Json formulated details for the new user", required = true)
            @RequestBody User user,
            @ApiParam(hidden = true) HttpServletResponse response) {
        User createdUser = userDB.saveUser(user);
        String location = "http://localhost/user/99"; //+ Integer.toString(createdUser.getUserId());
        URI locationUri = URI.create(location);
        response.addHeader(LOCATION, locationUri.toString());
        return createdUser;
    }

    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/

    @PutMapping
    @ApiOperation(httpMethod = "PUT", value = "Update user", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public String putUser(
            @ApiParam(value = "Json formulated user details which needs to be updated", required = true)
            @RequestBody User user
    ) {
        userDB.updateUser(user);

        return "The details have been updated for the user with id : " + user.getUserId();
    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/


    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE", value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has been updated"),
            @ApiResponse(code = 415, message = "Request cannot be read"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public String delete(
            @ApiParam(value = "Id of the user which needs to be deleted", required = true)
            @PathVariable("id") int uId
    ) {
        userDB.deleteUser(uId);
        return "The user with id " + uId + " has been deleted";
    }

}
