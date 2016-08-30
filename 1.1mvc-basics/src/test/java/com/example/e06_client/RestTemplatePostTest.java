package com.example.e06_client;

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
public class RestTemplatePostTest {
    RestTemplate restTemplate = new RestTemplate();

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;

    @Test
    public void testPostForObjectWithStringVarargs(){
        String response = restTemplate.postForObject("http://localhost:{port}/mapping",
                "1234567890", String.class, port);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "1234567890");
    }
}
