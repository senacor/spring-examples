package com.example.e04_profiles;

import com.example.e02_configurationproperties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 * This example illustrates how we can change the profile and get custom behavior.
 * Please set the property 'spring.profiles.active' to 'development'
 */
@RestController
@RequestMapping("/profiles/development")
public class ProfilesController {
    @Autowired
    private User user;

    // Now the user object will be {"firstName":"developmentName","lastName":"developmentLastName","email":"developmentEmail"}
    @RequestMapping("")
    public User developmentUser(){
        return user;
    }
}
