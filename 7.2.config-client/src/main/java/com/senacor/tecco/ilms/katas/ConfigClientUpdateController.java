package com.senacor.tecco.ilms.katas;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fsubasi on 26.01.2016.
 *
 * To enable a push of configuration updates, the spring cloud enabled application
 * offers an endpoint /refresh that can be invoked to trigger a refresh of
 * configuration values that are annotated with @refreshScope.
 *
 * As an alternative, spring cloud message bus can be used to enable a push of
 * configuration properties.
 *
 * This controller provides an endpoint updateUser to update values
 * in the config server to illustrate that beans can be reinitialized
 * without restarting the service.
 */

@RestController
public class ConfigClientUpdateController {

    String url = "http://localhost:8080/";
    RestTemplate restTemplate = new RestTemplate();

    private final User user;

    public ConfigClientUpdateController(User user) {
        this.user = user;
    }

    @RequestMapping("/updateUser")
    User updateUserEndpoint() {
        // curl --form "user.firstName=Max" http://localhost:8080/env
        updateEnvironment("user.firstName=Max");
        // curl --form "user.lastName=Mustermann" http://localhost:8080/env
        updateEnvironment("user.lastName=Mustermann");
        // curl --form "user.email=maxmustermann@example.com" http://localhost:8080/env
        updateEnvironment("user.email=maxmustermann@example.com");
        // curl -X POST http://localhost:8080/refresh
        refresh();
        return this.user;
    }

    // This method is used to update environment properties
    // another way to update would be with curl tool
    // for example: curl --form "property=value" http://localhost:8080/env
    // We could have also just as easily used System.setProperty("property", "value") here
    private void updateEnvironment(String propertyString){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(propertyString, requestHeaders);
        ResponseEntity<?> response = restTemplate.exchange(url + "env", HttpMethod.POST, requestEntity, Object.class);

    }

    // This method does an empty post to /refresh endpoint, which will refresh all beans annotated with @RefreshScope,
    // If their respective environment variables have been altered
    // curl equivalent: curl -X POST http://localhost:8080/refresh
    private void refresh(){
        restTemplate.postForEntity(url + "refresh", null, null);
    }
}
