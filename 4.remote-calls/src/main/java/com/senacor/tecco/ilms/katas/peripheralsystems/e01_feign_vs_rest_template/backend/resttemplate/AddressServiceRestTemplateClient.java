package com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.resttemplate;

import com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.model.Address;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This client uses spring internal {@link RestTemplate} for the communication with an external REST Service
 */
@Component
public class AddressServiceRestTemplateClient {

    private final RestTemplate restTemplate;

    public AddressServiceRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Simplest method to get a response from an external service is to return the actual json without any
     * deserialization
     */
    public String getRandomAddressAsString() {
        return restTemplate.getForObject("http://localhost:4711/randomAddress", String.class);
    }

    /**
     * Automatic deserialization of the response into a corresponding java bean
     */
    public Address getRandomAddressAsObject() {
        return restTemplate.getForObject("http://localhost:4711/randomAddress", Address.class);
    }

    /**
     * Return wrapper object containing more information on the response
     */
    public ResponseEntity<Address> getRandomAddressAsResponse() {
        return restTemplate.getForEntity("http://localhost:4711/randomAddress", Address.class);
    }

    /**
     * Lists are not supported by {@link RestTemplate}. Usage of Arrays as a workaround necessary
     */
    public List<Address> getAllAddressesWithoutFilterOption() {
        Address[] addresses = restTemplate.getForObject("http://localhost:4711/addressesWithoutFilterOption", Address[].class);

        if (addresses != null) {
            return Arrays.asList(addresses);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * url path variables need to be resolved in an external process before passing the url to the rest template
     */
    public List<Address> getUserAddressesByPathVariable(Integer userId) {

        String url = "http://localhost:4711/addressesByPathVariable/{userId}";

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("userId", userId.toString());

        Address[] addresses = restTemplate.getForObject(url, Address[].class, urlParams);

        if (addresses != null) {
            return Arrays.asList(addresses);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * url query parameters need to be resolved in an external process before passing the url to the rest template
     */
    public List<Address> getUserAddressesByQueryParam(Integer userId) {

        String url = "http://localhost:4711/addressesByQueryParam";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("userId", userId.toString());

        ResponseEntity<Address[]> addressesResponse = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                HttpEntity.EMPTY, Address[].class);

        if (addressesResponse.hasBody()) {
            return Arrays.asList(addressesResponse.getBody());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Invalid requests with a status code other than 2** will result in an exception
     */
    public String useNonExistingEndpoint() {
        return restTemplate.getForObject("http://localhost:4711/invalidMapping", String.class);
    }

    /**
     * Using request with request body - simply passed as an argument
     */
    public Address saveAddress(Address body) {
        return restTemplate.postForObject("http://localhost:4711/addresses", body, Address.class);
    }

}
