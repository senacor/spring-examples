package com.senacor.tecco.ilms.katas.e01_value;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This examples illustrates access to an configuration value configured
 * in application.yml using environment
 */

@RestController
public class EnvironmentController {

    //Autowire environment to access property information
    private final Environment environment;

    public EnvironmentController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping("/configuration/environment")
    public String appName(){
        return environment.getProperty("spring.application.name");
    }


}
