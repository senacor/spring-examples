package com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.feign;

import com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * This client uses the Feign framework for the communication with an external REST Service. This interface creates a
 * bean in the spring context and can simply be autowired in other services.
 */
@FeignClient(name = "example1ddresses", url = "localhost:4711")
public interface AddressServiceFeignClient {

    /**
     * Simplest method to get a response from an external service is to return the actual json without any
     * deserialization
     */
    @GetMapping("/randomAddress")
    String getRandomAddressAsString();

    /**
     * Automatic deserialization of the response into a corresponding java bean
     */
    @GetMapping( "/randomAddress")
    Address getRandomAddressAsObject();

    /**
     * Return wrapper object containing more information on the response
     */
    @GetMapping( "/randomAddress")
    ResponseEntity<Address> getRandomAddressAsResponse();

    /**
     * Native support for Collections
     */
    @GetMapping("/addressesWithoutFilterOption")
    List<Address> getAllAddressesWithoutFilterOption();

    /**
     * url path variables simply need to be templated in the url string and will then be resolved automatically
     */
    @GetMapping( "/addressesByPathVariable/{userId}")
    List<Address> getUserAddressesByPathVariable(@PathVariable("userId") Integer userId);

    /**
     * url query parameters will be added to the url automatically
     */
    @GetMapping( "/addressesByQueryParam")
    List<Address> getUserAddressesByQueryParam(@RequestParam("userId") Integer userId);

    /**
     * Invalid requests with a status code other than 2** will result in an exception
     */
    @GetMapping( "/invalidMapping")
    String useNonExistingEndpoint();

    /**
     * Using request with request body - simply passed as an argument
     */
    @PostMapping("/addresses")
    Address saveAddress(@RequestBody Address body);

}
