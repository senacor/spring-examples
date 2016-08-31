package com.senacor.tecco.ilms.katas.example.e06_client;

import com.senacor.tecco.ilms.katas.Application;
import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.response.UserResponse;
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
public class RestTemplateExchangeTest {
    RestTemplate restTemplate = new RestTemplate();

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;


    // Setting request headers with HttpEntity
    // We can get the response headers from ResponseEntity
    // Here we demonstrate a scenario where we post an object and get it back with its location in the response
    
    @Test
    public void testExchangeForLocationHttpEntity(){
    	User newUser = new User("Maria", "Liu", "marialiu@example.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> requestEntity = new HttpEntity<>(newUser, // Request Body
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