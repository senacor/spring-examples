package com.senacor.tecco.ilms.katas.e01_value;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValueAnnotatedServiceTest {

    @Autowired
    private ValueAnnotatedService valueAnnotatedService;

    @Test
    void valuesInjectedCorrectly() {
        String appNameConstructorInjected = valueAnnotatedService.getAppNameConstructorInjected();
        String appNameFieldInjected = valueAnnotatedService.getAppNameFieldInjected();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(appNameConstructorInjected).isEqualTo("Configuration Demo");
            softly.assertThat(appNameFieldInjected).isEqualTo("Configuration Demo");
        });
    }

}