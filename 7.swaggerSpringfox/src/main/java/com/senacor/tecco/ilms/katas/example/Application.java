package com.senacor.tecco.ilms.katas.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Modified by asiddiqui for Swagger configuration
 *
 * The methods "createUserApiSwaggerConfiguration" and "apiInfo" are Swagger configuration
 * methods.
 * They configure the API path and other REST API related information for Swagger documentation
 *
 */
@SpringBootApplication
@EnableSwagger2 //Enable swagger 2.0 spec
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket createUserApiSwaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("user-api")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/user.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Springfox Swagger demo API specification")
				.description("A demo application developed with Spring framework, providing an example for RESTful resources.")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.version("2.0")
				.build();
	}

}
