package com.senacor.tecco.ilms.katas.example.e02_contentnegotiation.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CsvConverterWebConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    CsvConverterWebConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvConverter<>(objectMapper));
    }
}
