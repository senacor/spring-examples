package com.example.e01_propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 */
@RestController
@RequestMapping("/propertySource")
public class PropertySourceController {

    @Autowired
    private User user;

    @RequestMapping("")
    public User customUser(){
        return user;
    }
}
