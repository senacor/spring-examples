package com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.feign;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.config.FeignClientConfiguration;
import com.senacor.tecco.ilms.katas.peripheralsystems.e02_jackson_mapping.backend.model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AddressServiceFeignClientTest {

    private WireMockServer wireMockServer;

    @Autowired
    private AddressServiceFeignClient addressServiceFeignClient;

    @BeforeEach
    void configureSystemUnderTest() {
        // Given
        this.wireMockServer = new WireMockServer(
                options()
                        .port(4711)
                        .usingFilesUnderClasspath("wiremock")
                        .extensions(new ResponseTemplateTransformer(true))
        );
        this.wireMockServer.start();
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }

    /**
     * The response of this call contains additional fields that are not defined in the model. The default object mapper
     * would thus fail during the deserialization. By using the object mapper defined in
     * {@link FeignClientConfiguration}, this behavior is overridden and deserialization succeeds
     */
    @Test
    void feignClientUsingCustomObjectMapper() {
        List<Address> addresses = addressServiceFeignClient.getExampleAddresses();

        assertThat(addresses).hasSize(3);
    }
}