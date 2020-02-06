package com.senacor.tecco.ilms.katas.e02_configurationproperties;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserConfigurationPropertiesAnnotatedConfigTest {

    @Autowired
    private User user;

    @Test
    void userCorrectlyInjected() {
        SoftAssertions.assertSoftly(softly -> {
           softly.assertThat(user.getFirstName()).isEqualTo("Michael");
           softly.assertThat(user.getLastName()).isEqualTo("Menzel");
           softly.assertThat(user.getEmail()).isEqualTo("michael.menzel@senacor.com");
        });
    }
}