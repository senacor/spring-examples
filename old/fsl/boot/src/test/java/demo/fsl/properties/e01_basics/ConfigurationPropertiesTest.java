package demo.fsl.properties.e01_basics;

import demo.fsl.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fsubasi on 14.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // If any @Configuration class is annotated with @EnableWebMvc, tests must include this annotation
// http://stackoverflow.com/questions/21516683/java-lang-illegalargumentexception-a-servletcontext-is-required-to-configure-de
@SpringApplicationConfiguration(classes = {Application.class, TestConfiguration.class}) //=>Spring equivalent @ContextConfiguration
public class ConfigurationPropertiesTest {
    @Autowired
    private Person person;

    // Property values can be injected directly into beans using the @Value
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

    @Test
    public void testPerson(){
        Assert.assertEquals(person.getEmail(), "email1");
        Assert.assertEquals(person.getName(), "name1");
        Assert.assertEquals(person.getTel(), 123456);
    }

    @Test
    public void testApplicationName(){
        Assert.assertEquals(applicationName, "spring-boot-demo-fsl");
    }

    @Test
    public void testEnvironment(){
        Assert.assertEquals(environment.getProperty("catname"), "bella");
        Assert.assertEquals(environment.getProperty("server.name"), "server1");
    }
}
