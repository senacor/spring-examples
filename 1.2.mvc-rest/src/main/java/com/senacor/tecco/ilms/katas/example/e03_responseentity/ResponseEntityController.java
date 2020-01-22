package com.senacor.tecco.ilms.katas.example.e03_responseentity;

import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import com.senacor.tecco.ilms.katas.common.response.UserResponse;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Created by fsubasi on 28.01.2016.
 *
 * In this example we see how to return a ResponseEntity
 * ResponseEntity carries metadata about a response.
 * In addition to setting the response body object, we can set
 * http headers and status code.
 */

@RestController
@RequestMapping("/responseEntity")
public class ResponseEntityController {

	UserService userService = new UserService();
	
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> createResponseEntity(@PathVariable("userId") int userId){
        // Use userId to get the user and return a UserResponse, here we just return a mock object
        UserResponse userResponse = new UserResponse(userService.getUserFromID(userId));

        // If we want to modify the headers
        // HttpHeaders headers = new HttpHeaders();
        // headers.set(....)
        // ResponseEntity<UserResponse> response = new ResponseEntity<>(userResponse, headers, HttpStatus.OK);

        // If we do not want to modify headers and status code is OK
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<BaseResponse> createResponseEntityWithLocation(@RequestBody User user){
        // Do something with user ...................
        UserResponse userResponse = new UserResponse(user);
        HttpHeaders headers = new HttpHeaders();
        // Create a mock location
        URI locationUri = URI.create("http://localhost/responseEntity/users/9");
        headers.setLocation(locationUri);
        // create a ResponseEntity with custom headers and status code
        return new ResponseEntity<>(userResponse, headers, HttpStatus.CREATED);
    }
}
