package com.example.e04_profiles;

import com.example.Application;
import com.example.e03_configurationproperties.User;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fsubasi on 11.02.2016.
 * Demonstrating @ActiveProfiles annotation
 */
@ActiveProfiles(profiles = "development") // Profiles that are active for this test
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ProfileTest {
    @Autowired
    private User user;

    @Test
    public void profileTest(){
        // The properties of @ConfigurationProperties annotated Person bean are overridden in application.yml
        // We test if these property values are same as it is for development profile the application.yml
        User expectedUser = new User();
        expectedUser.setFirstName("developmentName");
        expectedUser.setLastName("developmentLastName");
        expectedUser.setEmail("developmentEmail");

        Assert.assertThat(expectedUser, SamePropertyValuesAs.samePropertyValuesAs(user));
    }
}
