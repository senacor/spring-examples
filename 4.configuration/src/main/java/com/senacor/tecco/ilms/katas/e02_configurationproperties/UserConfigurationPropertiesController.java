package com.senacor.tecco.ilms.katas.e02_configurationproperties;

import org.springframework.beans.factory.annotation.Qualifier;
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

    private final User user;

    public UserConfigurationPropertiesController(@Qualifier("ConfigurationPropertiesUser") User user) {
        this.user = user;
    }

    @RequestMapping("configuration/property/user")
    public String getUser(){
        return user.toString();
    }
}
