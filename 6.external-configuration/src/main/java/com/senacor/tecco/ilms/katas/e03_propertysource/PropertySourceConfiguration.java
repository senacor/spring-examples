package com.senacor.tecco.ilms.katas.e03_propertysource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @PropertySource annotation demonstration
 * adding a property source to spring environment and use these properties to initialize beans
 */

@Configuration
// add a property source in the classpath
// use a placeholder to load additional property sources
@PropertySource("classpath:${config.directory}/user.properties")
public class PropertySourceConfiguration {

    @Bean(name = "propertySourceUser")
    public User user(){
        return new User();
    }

}
