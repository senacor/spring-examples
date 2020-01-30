package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.feign;

import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.config.Example2FeignConfiguration;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.config.FeignClientConfiguration;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * The object mapper used by this client is configured in {@link Example2FeignConfiguration}
 */
@FeignClient(name = "example2addresses", url = "localhost:4711", configuration = FeignClientConfiguration.class)
public interface AddressServiceFeignClient {

    @GetMapping(value = "/jacksonExampleAddresses")
    List<Address> getExampleAddresses();

}
