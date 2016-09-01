package com.senacor.tecco.ilms.katas.e02_hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * Created by fsubasi on 04.02.2016.
 * Hystrix Command Flow
 * 1- is the response cached? => get it from cache
 * 2- is circuit open? => fallback
 * 3- did semaphore/thread pool reject? => fallback
 * run() the command
 * 4- did execution fail? => fallback
 * 5- timeout? => fallback
 *
 */
@RestController
public class DemoController {
    public Random random = new Random();

    @RequestMapping("/hystrixDemoException")
    @HystrixCommand(commandKey = "demoException", fallbackMethod = "hystrixDemoFallback")
    public String hystrixDemoException(){
        if(true)
            throw new RuntimeException("Something is wrong");
        return "";
    }

    // Default timeout is 1000 milliseconds
    @RequestMapping("/hystrixDemoTimeout")
    @HystrixCommand(commandKey = "demoTimeout", fallbackMethod = "hystrixDemoFallback")
    public String hystrixDemoTimeout(){
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){
            throw new RuntimeException("thread interrupted.");
        }
        return "responseFromService";
    }

    // this endpoint is used to open the circuit for 'demoOpenCircuit' command.
    // to open the circuit we do 2 things
    // 1- post request to /env with "hystrix.command.demoOpenCircuit.circuitBreaker.forceOpen=true" request body
    // in application/x-www-form-urlencoded format to set the property
    // 2- an empty post request to /refresh to refresh the bean
    // TODO MM: Das Beispiel entfernen und besser die eigenen Beispiele hier hinzufügen
    @RequestMapping("/openCircuit")
    public String openCircuit(){
        String url = "http://localhost:8080/";
        String propertyString = "hystrix.command.demoOpenCircuit.circuitBreaker.forceOpen=true";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<String>(propertyString, requestHeaders);
        ResponseEntity<?> response = restTemplate.exchange(url + "env", HttpMethod.POST, requestEntity, Object.class);

        restTemplate.postForEntity(url + "refresh", null, Object.class);

        return "circuit opened";
    }


    // The point here is normally we will get the 'Standard response' and then we will call the /openCircuit endpoint above
    // to force this command's circuit open and then we will see the fallback response
    @RequestMapping("/hystrixOpenCircuit")
    @HystrixCommand(commandKey = "demoOpenCircuit", fallbackMethod = "hystrixDemoFallback")
    public String hystrixOpenCircuit(){
        return "Standard response";
    }

    private String standardCall(){
        /*int delay = random.nextInt(1000);
        if(delay > 400)
            System.out.println("Should reduce");*/
        int delay = 400;
        try{
            Thread.sleep(delay);
        } catch(InterruptedException e){
            throw new RuntimeException("thread interrupted.");
        }
        return "Standard call";
    }

    // Hystrix tried to revert to the fallback whenever a command execution fails:
    // when an exception is thrown by construct() or run()
    // when the command is short-circuited because the circuit is open
    // when the command’s thread pool and queue or semaphore are at capacity
    // when the command has exceeded its timeout length.
    public String hystrixDemoFallback(){
        return "Our servers are overloaded right now. Please try again later";
    }
}
