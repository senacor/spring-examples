package com.senacor.tecco.ilms.katas.example.e06_client;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fsubasi on 28.01.2016.
 * RestTemplate example: Here we see RestTemplate's GET convenience methods getForObject and getForEntity
 * in action. These methods have 3 overloaded versions. We demonstrate three overloaded variants of getForObject
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTemplateGetTest {
    // Reduces boilerplate of java.net.HttpUrlConnection
    // uses HttpMessageConverters to convert response into Java object
    RestTemplate restTemplate = new RestTemplate();

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;

    @Test
    void testGetForObjectWithURI() {
        URI uri = URI.create("http://localhost:" + port + "/basics/helloWorld");

        String str = restTemplate.getForObject(uri, // server url
                String.class // an object of type String is expected
        );

        Assert.assertEquals("Hello world!", str);
    }

    @Test
    void testGetForObjectWithStringAndMap() {
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("parameter", "myParameter"); // placeholder and its value

        String response =
                restTemplate.getForObject("http://localhost:" + port + "/mapping/pathVariable/{parameter}",
                        String.class,
                        templateParams);
        Assert.assertEquals("Parameter is myParameter", response);
    }

    @Test
    void testGetForObjectWithStringVarargs() {
        String response =
                restTemplate.getForObject("http://localhost:" + port + "/mapping/pathVariable/{parameter}",
                        String.class,
                        "myParameter"); // The last argument is vararg, i.e. placeholders can be defined one after the other following the sequence in URL

        Assert.assertEquals("Parameter is myParameter", response);
    }

    // Here we see how to get response status and response headers
    @Test
    void testGetForEntityWithStringVarargs() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://localhost:" + port + "/mapping/pathVariable/{parameter}",
                        String.class,
                        "myParameter");

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getHeaders());
        Assert.assertEquals("text/plain;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        Assert.assertEquals("Parameter is myParameter", responseEntity.getBody());

    }
}
