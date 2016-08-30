package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Sets up a Netflix Eureka service registry
public class ServiceRegistrationServer {

	public static void main(String[] args) {
		// Tell Boot to look for eureka-server.yml
		System.setProperty("spring.config.name", "eureka-server");
		SpringApplication.run(ServiceRegistrationServer.class, args);
	}
}
