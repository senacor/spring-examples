package com.senacor.tecco.ilms.katas.e01_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This examples illustrates access to an configuration value configured
 * in application.yml using injection with value annotation
 */
@Service
public class ValueAnnotatedService {

    @Value("${spring.application.name}")
    private String appNameFieldInjected;

    private final String appNameConstructorInjected;

    public ValueAnnotatedService(@Value("${spring.application.name}") String appName) {
        this.appNameConstructorInjected = appName;
    }

    public String getAppNameConstructorInjected() {
        return this.appNameConstructorInjected;
    }

    public String getAppNameFieldInjected() {
        return appNameFieldInjected;
    }
}
