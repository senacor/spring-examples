package com.senacor.tecco.ilms.katas.e04_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by fsubasi on 11.02.2016.
 * Demonstrating @ActiveProfiles annotation
 */
@ActiveProfiles(profiles = "development") // Profiles that are active for this test
@ExtendWith(SpringExtension.class)
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

        Assert.assertThat(expectedUser, SamePropertyValuesAs.samePropertyValuesAs(user));
    }
}
