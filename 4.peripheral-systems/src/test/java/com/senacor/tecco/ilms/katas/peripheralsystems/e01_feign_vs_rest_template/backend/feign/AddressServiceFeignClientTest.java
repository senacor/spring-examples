package com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.feign;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.model.Address;
import com.senacor.tecco.ilms.katas.peripheralsystems.e01_feign_vs_rest_template.backend.model.AddressType;
import feign.FeignException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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

    @Test
    void getRandomAddressAsString() {
        // When
        String randomAddressAsString = addressServiceFeignClient.getRandomAddressAsString();

        // Then
        assertThat(randomAddressAsString)
                .isNotNull()
                .contains("randomStreet");
    }

    @Test
    void getRandomAddressAsObject() {
        // When
        Address randomAddressAsObject = addressServiceFeignClient.getRandomAddressAsObject();

        // Then
        assertThat(randomAddressAsObject).isNotNull();
        assertThat(randomAddressAsObject.getStreet()).isEqualTo("randomStreet");
    }

    @Test
    void getRandomAddressAsResponse() {
        // When
        ResponseEntity<Address> randomAddressAsResponse = addressServiceFeignClient.getRandomAddressAsResponse();

        // Then
        assertThat(randomAddressAsResponse).isNotNull();
        assertThat(randomAddressAsResponse.hasBody()).isTrue();
        assertThat(randomAddressAsResponse.getBody())
                .isInstanceOf(Address.class)
                .satisfies(address -> assertThat(address.getStreet()).isEqualTo("randomStreet"));
        assertThat(randomAddressAsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(randomAddressAsResponse.getHeaders()).containsKey("content-type");
    }

    @Test
    void getAllAddressesWithoutFilterOption() {
        // When
        List<Address> allAddresses = addressServiceFeignClient.getAllAddressesWithoutFilterOption();

        // Then
        assertThat(allAddresses).hasSize(3);
    }

    @Test
    void getUserAddressesByPathVariable() {
        // When
        List<Address> userAddresses = addressServiceFeignClient.getUserAddressesByPathVariable(5);

        // Then
        assertThat(userAddresses).hasSize(1);
    }

    @Test
    void getUserAddressesByQueryParam() {
        // When
        List<Address> userAddresses = addressServiceFeignClient.getUserAddressesByQueryParam(5);

        // Then
        assertThat(userAddresses).hasSize(1);
    }

    @Test
    void useNonExistingEndpoint() {
        // When
        Throwable throwable = catchThrowable(() -> addressServiceFeignClient.useNonExistingEndpoint());

        // Then
        assertThat(throwable)
                .isInstanceOf(FeignException.class)
                .satisfies(t -> assertThat(((FeignException) t).status()).isEqualTo(404));
    }

    @Test
    void postBody() {
        // Given
        Address body = new Address();
        body.setStreet("street");
        body.setNumber("5A");
        body.setPostcode("A8136");
        body.setCity("city");
        body.setType(AddressType.HOME);

        // When
        Address savedAddress = addressServiceFeignClient.saveAddress(body);

        // Then
        assertThat(savedAddress).isEqualToComparingFieldByField(body);
    }
}