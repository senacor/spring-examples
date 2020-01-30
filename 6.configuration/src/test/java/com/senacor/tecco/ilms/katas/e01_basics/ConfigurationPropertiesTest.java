package com.senacor.tecco.ilms.katas.e01_basics;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(user.getFirstName()).isEqualTo("Michael");
        assertThat(user.getLastName()).isEqualTo("Menzel");
        assertThat(user.getEmail()).isEqualTo("michael.menzel@senacor.com");
    }

    @Test
    void testApplicationName() {
        assertThat(applicationName).isEqualTo("Configuration Demo");
    }

    @Test
    void testEnvironment() {
        assertThat(environment.getProperty("e02user.firstName")).isEqualTo("Michael");
        assertThat(environment.getProperty("e02user.lastName")).isEqualTo("Menzel");
        assertThat(environment.getProperty("e02user.email")).isEqualTo("michael.menzel@senacor.com");
    }
}
