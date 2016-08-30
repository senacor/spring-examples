package com.example.e06_client;

import com.common.model.User;
import com.common.response.UserResponse;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fsubasi on 17.02.2016.
 * RestTemplate demonstration. Look at tests for detailed information
 * ApplicationListener is used to get current port number not related to the example
 */

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController implements  ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    int port;

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/getForObjectWithURI")
    public String getForObjectWithURI() {
        URI uri = URI.create("http://localhost:" + port + "/requestMapping");

        return restTemplate.getForObject(uri, // server url
                String.class // an object of type String is expected
        );
    }
    @RequestMapping("/getForObjectWithStringAndMap")
    public String getForObjectWithStringAndMap(){
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("parameter", "myParameter"); // placeholder and its value

        return restTemplate.getForObject("http://localhost:" + port + "/requestMapping/templateParams/{parameter}",
                        String.class,
                        templateParams);
    }

    @RequestMapping("/postForObjectWithStringVarargs")
    public String postForObjectWithStringVarargs(){
        return restTemplate.postForObject("http://localhost:{port}/requestMapping",
                "1234567890", String.class, port);
    }

    @RequestMapping("/exchangeForLocationHttpEntity")
    public User exchangeForLocationHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> requestEntity = new HttpEntity<User>(new User("Brett", "Lee", "brettlee@example.com"), // Request Body
                headers);

        ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(
                "http://localhost:{port}/responseEntity/users",
                HttpMethod.POST, requestEntity, UserResponse.class, port);
        return responseEntity.getBody().getUser();
    }

    // not related to example just to get port no
    @Override
    public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
    }
}
