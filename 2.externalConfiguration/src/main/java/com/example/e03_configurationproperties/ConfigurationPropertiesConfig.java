package com.example.e03_configurationproperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fsubasi on 17.02.2016.
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
@Configuration
// @EnableConfigurationProperties(User.class) // => can be used to create beans from @ConfigurationProperties annotated classes
public class ConfigurationPropertiesConfig {

    // Instead of using @EnableConfigurationProperties, we can specify a bean directly
    @Bean
    public User user(){
        return new User();
    }
}
