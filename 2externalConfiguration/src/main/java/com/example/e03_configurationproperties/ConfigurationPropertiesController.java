package com.example.e03_configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 */
@RestController
@RequestMapping("/configurationProps")
@ConfigurationProperties("cat") // set methods in this controller will be initialized with external properties starting with 'cat'
public class ConfigurationPropertiesController {
    private String catName;

    // this value is set in other.properties file
    // the property's name is the @ConfigurationProperties value above + "." + property's name
    // which is cat.name in this case
    public void setName(String name){
        this.catName = name;
    }

    @RequestMapping("")
    public String catName(){
        return catName;
    }
}
