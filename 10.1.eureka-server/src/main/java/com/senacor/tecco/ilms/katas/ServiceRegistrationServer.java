package com.senacor.tecco.ilms.katas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka server and eureka client example
 * In this example, we would like to demonstrate service registration at
 * the eureka server and calls between a client service and a server service.
 *
 * Demonstration Walkthrough:
 * 1) Fire up the eureka-server
 * 2) Fire up the user service which is a eureka client
 *    and the service that will respond to requests for a
 *    certain User. It only has a simple controller that
 *    returns a mock User object.
 * 3) Fire up eureka-client-ribbon-feign service. This
 *    service makes requests to the user-service.
 *    Here the use of client-side load balancing using
 *    ribbon and feign is demonstrated in the context of
 *    requests to user-service. We demonstrate how to
 *    use service id instead of hostname/ip-address and
 *    get client-side load balancing for free
 */
@SpringBootApplication
@EnableEurekaServer // Sets up a Netflix Eureka service registry
public class ServiceRegistrationServer {

	public static void main(String[] args) {
		// Tell Boot to look for eureka-server.yml
		System.setProperty("spring.config.name", "eureka-server");
		SpringApplication.run(ServiceRegistrationServer.class, args);
	}
}
