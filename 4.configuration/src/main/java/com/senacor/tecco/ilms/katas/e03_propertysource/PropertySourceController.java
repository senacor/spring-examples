package com.senacor.tecco.ilms.katas.e03_propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 17.02.2016.
 *
 * Spring Boot will draw properties from the following, in order of precendence
 * 1 Command-line arguments
 * 2 JNDI attributes from java:comp/env
 * 3 JVM system properties
 * 4 Operating system environment variables
 * 5 Randomly generated values for properties prefixed with random.* (referenced when setting other properties, such as `${random.long})
 * 6 An application.properties or application.yml file outside of the application
 * 7 An application.properties or application.yml file packaged inside of the application
 * 8 Property sources specified by @PropertySource
 * 9 Default properties
 */
@RestController
@RequestMapping("/source")
public class PropertySourceController {

    @Autowired
    @Qualifier("propertySourceUser")
    private User user;

    @RequestMapping("/property/user")
    public User customUser(){
        return user;
    }
}
