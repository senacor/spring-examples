package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.config;

import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.feign.AddressServiceFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {AddressServiceFeignClient.class})
public class Example2FeignConfiguration {
}
