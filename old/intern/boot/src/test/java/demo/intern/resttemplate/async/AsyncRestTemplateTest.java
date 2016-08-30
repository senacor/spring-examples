package demo.intern.resttemplate.async;

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
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * Created by fsubasi on 11.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = {Application.class, TestConfiguration.class})
public class AsyncRestTemplateTest {

    // Discover the HTTP port at runtime
    @Value("${local.server.port}")
    int port;

    @Test
    public void asyncRestTemplateFutureGet() throws Exception{ // using java.util.concurrent.Future's blocking get method
        AsyncRestTemplate asyncRest = new AsyncRestTemplate();
        String url ="http://localhost:{port}/responseEntity/users/{userId}";
        Class<User> responseType = User.class;

        ListenableFuture<ResponseEntity<User>> future = asyncRest.getForEntity(url, responseType,
                port,
                5); // mock user id, value of the {userId} placeholder in url

        // block until result
        ResponseEntity<User> response = future.get();
        Assert.assertNotNull(response);

        User user = User.createUser();
        Assert.assertNotNull(response);
        Assert.assertThat(user, SamePropertyValuesAs.samePropertyValuesAs(response.getBody()));
    }

    @Test
    public void asyncRestTemplateCallback() throws Exception{
        AsyncRestTemplate asyncRest = new AsyncRestTemplate();
        String url ="http://localhost:{port}/responseEntity/users/{userId}";
        Class<User> responseType = User.class;

        ListenableFuture<ResponseEntity<User>> future = asyncRest.getForEntity(url, responseType,
                port,
                5); // mock user id, value of the {userId} placeholder in url

        User user = User.createUser();

        // add callbacks Promise.then style
        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Assert.fail("Backend call failed: " + throwable.toString());
            }

            @Override
            public void onSuccess(ResponseEntity<User> response) {
                Assert.assertNotNull(response);
                Assert.assertThat(user, SamePropertyValuesAs.samePropertyValuesAs(response.getBody()));
            }
        });
        // We can add as many callbacks as we want, now adding the same callback this time using lambdas
        future.addCallback(response -> { // first we add the callback for success case
                    Assert.assertNotNull(response);
                    Assert.assertThat(user, SamePropertyValuesAs.samePropertyValuesAs(response.getBody()));
                },
                throwable-> Assert.fail("Backend call failed: " + throwable.toString())); // failure case

        // Wait until the response arrives to evaluate the results
        future.get();
    }

    /**
     * Here we are demonstrating a case where we do async operations one after the other
     * Similar to <code>var promise = Promise.then(operation1).then(operation2);</code>
     */
    @Test
    public void asyncCompletableFutureCompose() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String userUrl ="http://localhost:{port}/responseEntity/users/{userId}";
        Class<User> responseType = User.class;

        CompletableFuture<ResponseEntity<User>> userFuture = CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(userUrl, responseType, port, 5));

        User user = User.createUser();

        CompletableFuture<?> end = userFuture.thenCompose(response -> {
            Assert.assertNotNull(response);
            Assert.assertThat(user, SamePropertyValuesAs.samePropertyValuesAs(response.getBody()));
            return CompletableFuture.supplyAsync(() -> response);
        });

        // Wait until everything is processed
        end.get();
    }


    /**
     * Here we demonstrate a case where we want multiple async operations to be performed concurrently.
     * After all of them have completed we collect the results to do something else
     * Similar to <code>var promise = Promise.all([promise1, promise2,...])</code>
     */
    @Test
    public void asyncCompletableFutureCombine() throws Exception{
        User user = User.createUser();

        // First backend call
        RestTemplate restTemplate = new RestTemplate();
        String userUrl ="http://localhost:{port}/responseEntity/users/{userId}";
        Class<User> firstResponseType = User.class;

        CompletableFuture<ResponseEntity<User>> userFuture =
                CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(userUrl, firstResponseType, port, 5));

        // Second backend call
        String helloWorldUrl = "http://localhost:{port}/basics/helloWorldRest";
        Class<String> secondResponseType = String.class;

        CompletableFuture<ResponseEntity<String>> helloWorldFuture =
                CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(helloWorldUrl, secondResponseType, port));

        userFuture.thenCombine(helloWorldFuture,
                (userResponse, helloWorldResponse) -> {
                    Assert.assertThat(user, SamePropertyValuesAs.samePropertyValuesAs(userResponse.getBody()));
                    Assert.assertEquals("Hello World!", helloWorldResponse.getBody());
                    return "";
                })
                // Wait until requests are processed
                .get();
    }
}
