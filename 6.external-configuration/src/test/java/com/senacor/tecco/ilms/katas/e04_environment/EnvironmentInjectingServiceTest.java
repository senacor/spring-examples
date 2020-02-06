package com.senacor.tecco.ilms.katas.e04_environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EnvironmentInjectingServiceTest {

    @Autowired
    private EnvironmentInjectingService environmentInjectingService;

    @Test
    void valuesInjectedCorrectly() {
        String appNameConstructorInjected = environmentInjectingService.getAppName();

        assertThat(appNameConstructorInjected).isEqualTo("Configuration Demo");
    }

}