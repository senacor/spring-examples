package demo.intern.properties.e02_profiles;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by fsubasi on 09.02.2016.
 * In this example we want to show that we can change the configuration with profiles
 * Suppose that during development, we would like to serve static files from some local directory instead
 * of the typical places spring boot looks for static content. Here we will also show how to customize configuration
 * using profile specific .properties files and multi-profile .yaml files.(application-development.properties and application.yml files)
 *
 * The active profiles are set with the property 'spring.profiles.active', to set multiple profiles
 * we can use commas, i.e. spring.profiles.active=internal,development
 */
@Profile("development") // This configuration is only enabled if the active profile is development
@Component
public class CustomWebMvcAutoConfigurationAdapter extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:/path/to/my/web/folder/");
    }
}
