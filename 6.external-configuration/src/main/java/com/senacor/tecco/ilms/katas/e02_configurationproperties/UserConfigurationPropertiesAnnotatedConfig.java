package com.senacor.tecco.ilms.katas.e02_configurationproperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(User.class)// => can be used to create beans from @ConfigurationProperties annotated classes
public class UserConfigurationPropertiesAnnotatedConfig {

}
