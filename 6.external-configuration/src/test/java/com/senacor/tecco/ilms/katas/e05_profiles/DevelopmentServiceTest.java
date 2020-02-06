package com.senacor.tecco.ilms.katas.e05_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("development")
@SpringBootTest
class DevelopmentServiceTest {

    @Autowired
    private ProfileDependentService profileDependentService;

    @Test
    void correctServiceBeanInstantiatet() {
        String profile = profileDependentService.getProfile();
        User user = profileDependentService.getUser();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(profile).isEqualTo("development");
            softly.assertThat(user.getFirstName()).isEqualTo("developmentName");
        });
    }
}