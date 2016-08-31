package com.example.e01_basics;

import com.example.Application;
import com.example.e03_configurationproperties.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fsubasi on 14.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class}) //=>Spring equivalent @ContextConfiguration
public class ConfigurationPropertiesTest {
    @Autowired
    private User user;

    // Property values can be injected directly into beans using the @Value
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

    @Test
    public void testPerson(){
        Assert.assertEquals(user.getFirstName(), "Samwise");
        Assert.assertEquals(user.getLastName(), "Gamgee");
        Assert.assertEquals(user.getEmail(), "sgamgee@shire.com");
    }

    @Test
    public void testApplicationName(){
        Assert.assertEquals(applicationName, "2externalConfiguration");
    }

    @Test
    public void testEnvironment(){
        Assert.assertEquals("bella", environment.getProperty("cat.name"));
        Assert.assertEquals("Peregrin", environment.getProperty("firstName"));
        Assert.assertEquals("Took", environment.getProperty("lastName"));
        Assert.assertEquals("ptook@shire.com", environment.getProperty("email"));
    }
}
