package com.senacor.tecco.ilms.katas.e01_basics;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by fsubasi on 14.01.2016.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConfigurationPropertiesTest {
    @Autowired
    private User user;

    // Property values can be injected directly into beans using the @Value
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

    @Test
    void testPerson() {
        Assert.assertEquals(user.getFirstName(), "Michael");
        Assert.assertEquals(user.getLastName(), "Menzel");
        Assert.assertEquals(user.getEmail(), "michael.menzel@senacor.com");
    }

    @Test
    void testApplicationName() {
        Assert.assertEquals(applicationName, "Configuration Demo");
    }

    @Test
    void testEnvironment() {
        Assert.assertEquals("Michael", environment.getProperty("e02user.firstName"));
        Assert.assertEquals("Menzel", environment.getProperty("e02user.lastName"));
        Assert.assertEquals("michael.menzel@senacor.com", environment.getProperty("e02user.email"));
    }
}
