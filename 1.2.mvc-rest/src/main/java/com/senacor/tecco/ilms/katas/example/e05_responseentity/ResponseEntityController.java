package com.senacor.tecco.ilms.katas.example.e05_responseentity;

import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> createResponseEntity(@PathVariable("userId") int userId){
        // Use userId to get the user, here we just return a mock object
        User user =userService.getUserFromID(userId);

        // If we want to modify the headers
        // HttpHeaders headers = new HttpHeaders();
        // headers.set(....)
        // ResponseEntity<UserResponse> response = new ResponseEntity<>(userResponse, headers, HttpStatus.OK);

        // If we do not want to modify headers and status code is OK
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createResponseEntityWithLocation(@RequestBody User user){
        // Do something with user ...................
        HttpHeaders headers = new HttpHeaders();
        // Create a mock location
        URI locationUri = URI.create("http://localhost/responseEntity/users/9");
        headers.setLocation(locationUri);
        // create a ResponseEntity with custom headers and status code
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }
}
