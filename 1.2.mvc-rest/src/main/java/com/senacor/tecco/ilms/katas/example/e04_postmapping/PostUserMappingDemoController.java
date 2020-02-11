package com.senacor.tecco.ilms.katas.example.e04_postmapping;

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
@RequestMapping("/postmapping")
public class PostUserMappingDemoController {

	UserService userService = new UserService();

    @PostMapping("/users")
    public User createResponseEntityWithLocation(@RequestBody User user){
        // Do something with user ...................

        return user;
    }
}
