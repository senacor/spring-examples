package com.example.e01_propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by fsubasi on 17.02.2016.
 * @PropertySource annotation demonstration
 * adding a property source to spring environment and use these properties to initialize beans
 */
@Configuration
@PropertySource("classpath:other.properties") // add a property source in the classpath
@PropertySource("classpath:${config.directory}/user.properties") // use a placeholder to load additional property sources
public class PropertySourceConfiguration {

    @Autowired
    Environment environment;

    @Bean
    public User customUser(){
        User user = new User();
        user.setFirstName(environment.getProperty("firstName")); // these properties are defined in configdir/user.properties file
        user.setLastName(environment.getProperty("lastName"));
        user.setEmail(environment.getProperty("email"));

        return user;
    }

}
