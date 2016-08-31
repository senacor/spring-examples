package com.example.e02_configurationproperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
// @EnableConfigurationProperties(User.class) // => can be used to create beans from @ConfigurationProperties annotated classes
public class UserConfigurationPropertiesConfig {

    // Instead of using @EnableConfigurationProperties, we can specify a bean directly
    @Bean(name = "ConfigurationPropertiesUser")
    public User user(){
        return new User();
    }
}
