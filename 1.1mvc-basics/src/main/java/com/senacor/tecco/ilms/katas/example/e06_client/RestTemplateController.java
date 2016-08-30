package com.senacor.tecco.ilms.katas.example.e06_client;

import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.response.UserResponse;

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
 *
 * RestTemplate demonstration to invoke rest endpoints.
 * ApplicationListener is used to get current port number not related to the example
 */

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    int port;

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/getForObject")
    public String getForObjectWithURI() {
        URI uri = URI.create("http://localhost:" + port + "/basics/helloWorldRest");

        return restTemplate.getForObject(uri, // server url
                String.class // an object of type String is expected
        );
    }

    @RequestMapping("/getForObjectWithParam")
    public String getForObjectWithStringAndMap(){
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("parameter", "myParameter"); // placeholder and its value

        return restTemplate.getForObject("http://localhost:" + port + "/mapping/pathVariable/{parameter}",
                        String.class,
                        templateParams);
    }

    @RequestMapping("/postForObject")
    public String postForObjectWithStringVarargs(){
        return restTemplate.postForObject("http://localhost:{port}/mapping",
                "1234567890", String.class, port);
    }

    @RequestMapping("/exchangeForLocationHttpEntity")
    public User exchangeForLocationHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> requestEntity = new HttpEntity<>(new User("Brett", "Lee", "brettlee@example.com"), // Request Body
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
