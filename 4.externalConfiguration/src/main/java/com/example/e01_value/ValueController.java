package com.example.e01_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This examples illustrates access to an configuration value configured
 * in application.yml using injection with value annotation
 */

@RestController
public class ValueController {

    //inject value from application.yml to appName
    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/configuration/value")
    public String appName(){
        return this.appName;
    }


}
