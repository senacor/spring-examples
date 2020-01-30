package com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.config;

import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Example1RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
         return builder.requestFactory(this::getClientHttpRequestFactory).build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }
}
