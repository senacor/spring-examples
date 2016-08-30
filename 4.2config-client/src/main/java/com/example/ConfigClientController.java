package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fsubasi on 26.01.2016.
 * Here the injected umgebung and user beans are initialized with configuration from config server
 * A possible order of topics here:
 * 1- bootstrap.yml, specifying config-server location, need to specify spring.application.name in bootstrap.yml
 * to be able to locate service-specific configuration
 * 2- /user and /environment endpoints which display beans that are initialized with configuration from config-server
 * 3- change spring.active.profiles to development, redeploy and see beans initialized with profile-specific configuration
 * 4- @RefreshScope annotation(in ConfigClientApplication class), /env and /refresh endpoints and show we can push
 * configuration and how the beans can be reinitialized without restarting the service(demonstrated in '/updateUser')
 *
 * TODO: Spring Cloud Bus example and show how the config-client service can pull latest configuration from config-server
 * TODO: and reinitialize the beans
 */

@RestController
public class ConfigClientController {
    String url = "http://localhost:8080/";
    RestTemplate restTemplate = new RestTemplate();


    // This value is shared by all clients
    @Value("${umgebung}")
    private String umgebung;

    @Autowired
    User user;

    @RequestMapping("/user")
    User userEndpoint() {
        return this.user;
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


    @RequestMapping("/environment")
    String environment(){
        return umgebung;
    }

    // This method is used to update environment properties
    // another way to update would be with curl tool
    // for example: curl --form "property=value" http://localhost:8080/env
    // We could have also just as easily used System.setProperty("property", "value") here
    private void updateEnvironment(String propertyString){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<String>(propertyString, requestHeaders);
        ResponseEntity<?> response = restTemplate.exchange(url + "env", HttpMethod.POST, requestEntity, Object.class);

    }

    // This method does an empty post to /refresh endpoint, which will refresh all beans annotated with @RefreshScope,
    // If their respective environment variables have been altered
    // curl equivalent: curl -X POST http://localhost:8080/refresh
    private void refresh(){
        restTemplate.postForEntity(url + "refresh", null, null);
    }
}
