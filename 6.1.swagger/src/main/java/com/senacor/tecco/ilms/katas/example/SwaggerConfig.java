package com.senacor.tecco.ilms.katas.example;

import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.context.annotation.Configuration;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by asiddiqui on 23/03/16.
 *
 * The methods "createUserApiSwaggerConfiguration" and "apiInfo" are Swagger configuration
 * methods.
 * They configure the API path and other REST API related information for Swagger documentation
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
    private SpringSwaggerConfig springSwaggerConfig;
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
    // Don't forget the @Bean annotation
    @Bean
    public SwaggerSpringMvcPlugin createUserApiSwaggerConfiguration() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
                apiInfo()).includePatterns("/user", "/user/.*");
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Demo API for Swagger", "API for demo project",
                "Project terms of service", "aarij.siddiqui@senacor.com",
                "Project Licence Type", "Project License URL");
        return apiInfo;
    }
}
