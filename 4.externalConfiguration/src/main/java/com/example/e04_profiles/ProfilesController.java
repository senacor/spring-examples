package com.example.e04_profiles;

import com.example.e02_configurationproperties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 * Here we show how we can change the profile and get custom behavior.
 * Before this demonstration, the 'spring.profiles.active' property must be set to 'development'
 * in any one of the possible ways, then we observe custom behavior because of the development profile
 * configuration in application.yml file
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
