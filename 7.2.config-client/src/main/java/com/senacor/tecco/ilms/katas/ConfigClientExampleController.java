package com.senacor.tecco.ilms.katas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 26.01.2016.
 *
 * In this example, the properties umgebung and user are injected and
 * initialized with configuration from config server.
 *
 * 1) See bootstrap.yml for spring cloud configuration: enabling the config server and configuring its location
 * 2) The endpoints /user and /environment in this controller return values provided by the config-server
 * 3) change spring.active.profiles to development, redeploy and see beans initialized with profile-specific configuration
 */

@RestController
public class ConfigClientExampleController {

    // This value is provided by the config server
    @Value("${umgebung}")
    private String umgebung;

    // This value is initialized with data from the config server
    @Autowired
    User user;

    //entpoint to retrieve user configuration data
    @RequestMapping("/user")
    User userEndpoint() {
        return this.user;
    }

    //entpoint to retrieve environment configuration property
    @RequestMapping("/environment")
    String environment(){
        return umgebung;
    }

}
