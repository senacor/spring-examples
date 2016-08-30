package com.example.e05_client;

import com.Application;
import com.common.model.User;
import com.common.response.UserResponse;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fsubasi on 28.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = {Application.class})
public class RestTemplateOtherMethodsTest {
    RestTemplate restTemplate = new RestTemplate();

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;

    @Test
    public void testPostForObjectWithStringVarargs(){
        String response = restTemplate.postForObject("http://localhost:{port}/requestMapping",
                "1234567890", String.class, port);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "1234567890");
    }

    // Setting request headers with HttpEntity
    // We can get the response headers from ResponseEntity
    // Here we demonstrate a scenario where we post an object and get it back with its location in the response
    
    @Test
    public void testExchangeForLocationHttpEntity(){
    	User newUser = new User("Maria", "Liu", "marialiu@example.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> requestEntity = new HttpEntity<User>(newUser, // Request Body
                headers);

        ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(
                "http://localhost:{port}/responseEntity/users",
                HttpMethod.POST, requestEntity, UserResponse.class, port);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals("http://localhost/responseEntity/users/9", // backend always responds with the same location header
                responseEntity.getHeaders().getLocation().toString());

        Assert.assertTrue(responseEntity.hasBody());
        UserResponse response = responseEntity.getBody();
        Assert.assertTrue(response.get_successful());
        Assert.assertTrue(response.get_messages().isEmpty());
        Assert.assertThat(newUser, SamePropertyValuesAs.samePropertyValuesAs(response.getUser()));
    }
    
}
