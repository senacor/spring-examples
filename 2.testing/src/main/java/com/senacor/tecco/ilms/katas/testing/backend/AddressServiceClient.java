package com.senacor.tecco.ilms.katas.testing.backend;

import com.senacor.tecco.ilms.katas.testing.backend.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "addresses", url = "localhost:4711")
public interface AddressServiceClient {

    @GetMapping(value = "/addresses/{userId}")
    List<Address> getUserAddresses(@PathVariable("userId") Integer userId);

}
