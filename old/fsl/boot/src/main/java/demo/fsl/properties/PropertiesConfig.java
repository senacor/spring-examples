package demo.fsl.properties;

import demo.fsl.properties.e01_basics.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by fsubasi on 13.01.2016.
 * Spring Boot will draw properties from the following, in order of precendence
 * 1 Command-line arguments
   2 JNDI attributes from java:comp/env
   3 JVM system properties
   4 Operating system environment variables
   5 Randomly generated values for properties prefixed with random.* (referenced when setting other properties, such as `${random.long})
   6 An application.properties or application.yml file outside of the application
   7 An application.properties or application.yml file packaged inside of the application
   8 Property sources specified by @PropertySource
   9 Default properties
 */
@Configuration
@ComponentScan
// @EnableConfigurationProperties(Person.class) => can be used to create beans from @ConfigurationProperties annotated classes
@PropertySource("classpath:other.properties") // we can add a property source in the classpath
@PropertySource("classpath:${config.directory}/app.properties") // we can even use property values to add additional property sources
public class PropertiesConfig {
    // Instead of using @EnableConfigurationProperties, we can specify a bean directly
    @Bean
    public Person person(){
        return new Person();
    }
}
