package com.example.e01_basics;

import com.example.Application;
import com.example.e02_configurationproperties.User;
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
        Assert.assertEquals(user.getFirstName(), "Michael");
        Assert.assertEquals(user.getLastName(), "Menzel");
        Assert.assertEquals(user.getEmail(), "michael.menzel@senacor.com");
    }

    @Test
    public void testApplicationName(){
        Assert.assertEquals(applicationName, "Configuration Demo");
    }

    @Test
    public void testEnvironment(){
        Assert.assertEquals("Michael", environment.getProperty("e02user.firstName"));
        Assert.assertEquals("Menzel", environment.getProperty("e02user.lastName"));
        Assert.assertEquals("michael.menzel@senacor.com", environment.getProperty("e02user.email"));
    }
}
