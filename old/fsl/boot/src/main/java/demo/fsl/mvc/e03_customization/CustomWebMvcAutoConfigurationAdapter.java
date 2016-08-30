package demo.fsl.mvc.e03_customization;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by fsubasi on 03.02.2016.
 * MVC configuration with WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 * For demonstrating the configuration done in FSLWebMvcConfigurer in common-fsl-service
 */
@Component
@EnableWebMvc
public class CustomWebMvcAutoConfigurationAdapter extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/book/**").addResourceLocations("desk", "classpath:chair/");
    }
}
