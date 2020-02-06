package com.senacor.tecco.ilms.katas.e04_environment;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * This examples illustrates access to an configuration value configured
 * in application.yml using environment
 */
@Service
public class EnvironmentInjectingService {

    //Autowire environment to access property information
    private final Environment environment;

    public EnvironmentInjectingService(Environment environment) {
        this.environment = environment;
    }

    public String getAppName() {
        return environment.getProperty("spring.application.name");
    }


}
