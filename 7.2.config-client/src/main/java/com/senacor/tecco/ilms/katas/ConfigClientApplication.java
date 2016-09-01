package com.senacor.tecco.ilms.katas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ConfigClientApplication {
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	// We declare a bean here that is initialized with property values from environment
	// this bean will be re-initialized when configuration changes
	@RefreshScope
	@Bean
	public User user(){
		return new User();
	}
}