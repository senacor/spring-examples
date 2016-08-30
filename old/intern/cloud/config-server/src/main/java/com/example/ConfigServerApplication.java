package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
// @EnableDiscoveryClient => To register to EUREKA, if no eureka is present gives annoying failure messages
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "config-server");
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}