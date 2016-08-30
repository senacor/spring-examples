package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 21.01.2016.
 */
@RestController
public class UsersServiceController {

    @RequestMapping("/user/{id}")
    public User byNumber(@PathVariable("id") int userId) {
        return createUser();
    }

    private User createUser(){
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailAddress("johndoe@example.com");
        return user;
    }
}
