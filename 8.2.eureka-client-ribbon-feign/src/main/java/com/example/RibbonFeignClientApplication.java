package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RibbonFeignClientApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "ribbon-feign-client-server");
		SpringApplication.run(RibbonFeignClientApplication.class, args);
	}
}

