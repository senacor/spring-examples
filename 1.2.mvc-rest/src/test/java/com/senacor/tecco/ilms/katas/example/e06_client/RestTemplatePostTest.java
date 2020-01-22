package com.senacor.tecco.ilms.katas.example.e06_client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by fsubasi on 28.01.2016.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTemplatePostTest {
    RestTemplate restTemplate = new RestTemplate();

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;

    @Test
    void testPostForObjectWithStringVarargs() {
        String response = restTemplate.postForObject("http://localhost:{port}/mapping",
                "1234567890", String.class, port);

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo("1234567890");
    }
}
