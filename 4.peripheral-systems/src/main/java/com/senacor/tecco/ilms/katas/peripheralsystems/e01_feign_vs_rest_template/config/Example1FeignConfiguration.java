package com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.config;

import com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.feign.AddressServiceFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {AddressServiceFeignClient.class})
public class Example1FeignConfiguration {
}
