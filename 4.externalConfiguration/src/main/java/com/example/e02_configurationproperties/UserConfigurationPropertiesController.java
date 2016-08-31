package com.example.e02_configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 *
 * The user object in this example is initialized
 * with with configuration values provided in application.properties
 */

@RestController
@RequestMapping("")
public class UserConfigurationPropertiesController {

    @Autowired
    @Qualifier("ConfigurationPropertiesUser")
    private User user;

    @RequestMapping("configuration/property/user")
    public String getUser(){
        return user.toString();
    }
}
