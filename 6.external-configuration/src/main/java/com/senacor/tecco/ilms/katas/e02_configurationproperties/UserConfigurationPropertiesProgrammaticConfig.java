package com.senacor.tecco.ilms.katas.e02_configurationproperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfigurationPropertiesProgrammaticConfig {

    // Instead of using @EnableConfigurationProperties, we can specify a bean directly
    @Bean(name = "ConfigurationPropertiesUser")
    public User user(){
        return new User();
    }
}
