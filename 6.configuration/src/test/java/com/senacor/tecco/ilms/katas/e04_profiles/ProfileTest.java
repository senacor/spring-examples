package com.senacor.tecco.ilms.katas.e04_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Demonstrating @ActiveProfiles annotation
 */
@ActiveProfiles(profiles = "development") // Profiles that are active for this test
@SpringBootTest
class ProfileTest {
    @Autowired
    private User user;

    @Test
    void profileTest() {
        // The properties of @ConfigurationProperties annotated Person bean are overridden in application.yml
        // We test if these property values are same as it is for development profile the application.yml
        User expectedUser = new User();
        expectedUser.setFirstName("developmentName");
        expectedUser.setLastName("developmentLastName");
        expectedUser.setEmail("developmentEmail");

        assertThat(user).isEqualToComparingFieldByField(expectedUser);
    }
}
