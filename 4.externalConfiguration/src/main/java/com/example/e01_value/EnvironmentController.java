package com.example.e01_value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This examples illustrates access to an configuration value configured
 * in application.yml using environment
 */

@RestController
public class EnvironmentController {

    //Autowire environment to access property information
    @Autowired
    Environment environment;

    @RequestMapping("/configuration/environment")
    public String appName(){
        return environment.getProperty("spring.application.name");
    }


}
