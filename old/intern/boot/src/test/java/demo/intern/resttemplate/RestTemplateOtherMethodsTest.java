package demo.intern.resttemplate;

import demo.intern.Application;
import demo.intern.mvc.User;
import demo.intern.properties.e01_basics.TestConfiguration;
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
@SpringApplicationConfiguration(classes = {Application.class, TestConfiguration.class})
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
    // Here we demonstrate a scenario where we post an object and get its location in the response
    @Test
    public void testExchangeForLocationHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(User.createUser(), // Request Body
                headers);

        ResponseEntity<?> response = restTemplate.exchange(
                "http://localhost:{port}/responseEntity/users",
                HttpMethod.POST, requestEntity, User.class, port);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.hasBody()); // The response has no body. See demo.intern.mvc.e01_basics.e03_responseentity.ResponseEntityController
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals("http://localhost/responseEntity/users/2", // backend always responds with the same location header
                response.getHeaders().getLocation().toString());
    }
}
