package com.senacor.tecco.ilms.katas.testing.e05_mocking_external_services;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import feign.FeignException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * Wiremock can be used to mock external services and their responses. The definition of the external api can be found
 * in folder test/resources/wiremock.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WireMock {

    private WireMockServer wireMockServer;

    @Autowired
    private UserService userService;

    /**
     * Set up wiremock server in background using the api definition found in the test resource folder
     */
    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer(
                options()
                        .port(4711)
                        .usingFilesUnderClasspath("wiremock")
        );
        this.wireMockServer.start();
    }

    /**
     * Shutdown wiremock server
     */
    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }

    /**
     * Tested method relies on a client for an external service communicating via rest calls.
     */
    @Test
    void findUserAddress_usedIdMatchingWiremockSetup() {
        List<String> addressForUserWithId = userService.getAddressForUserWithId(12);

        assertThat(addressForUserWithId)
                .hasSize(1)
                .containsOnly(
                        "testStreet testNumber\n" +
                        "testPostcode testCity"
                );
    }

    @Test
    void findUserAddress_usedIdNotMatchingWiremockSetup() {
        assertThatThrownBy(() -> userService.getAddressForUserWithId(123))
                .isInstanceOf(FeignException.class)
                .satisfies(exception -> assertThat(((FeignException) exception).status()).isEqualTo(404));
    }


}
