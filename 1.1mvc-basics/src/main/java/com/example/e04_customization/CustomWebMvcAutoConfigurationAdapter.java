package com.example.e04_customization;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by fsubasi on 03.02.2016.
 * MVC configuration with WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 * For demonstrating the configuration done in FSLWebMvcConfigurer in common-fsl-service
 */
@Component
public class CustomWebMvcAutoConfigurationAdapter extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // normally for a controller method annotated with @RequestMapping("/user")
        // spring matches all of the following requests to this controller method
        // .../user   .../user.xml  .../user.json, the following configuration in common-fsl-service
        // cancels this behavior, i.e. only .../user will be intercepted by the controller method
        configurer.setUseSuffixPatternMatch(false);
        configurer.setUseRegisteredSuffixPatternMatch(false);
    }

    // Add handlers to serve static resources such as images, js, and, css files from specific locations
    // under web application root, the classpath, and others. This method overrides the default Spring Boot
    // static resource locations
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("classpath:static/");
    }
}