package com.senacor.tecco.ilms.katas.e03_propertysource;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertySourceConfigurationTest {

    @Autowired
    @Qualifier("propertySourceUser")
    private User user;

    @Test
    void userCorrectlyInjected() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(user.getFirstName()).isEqualTo("Test");
            softly.assertThat(user.getLastName()).isEqualTo("User");
            softly.assertThat(user.getEmail()).isEqualTo("whatever@senacor.com");
        });
    }
}