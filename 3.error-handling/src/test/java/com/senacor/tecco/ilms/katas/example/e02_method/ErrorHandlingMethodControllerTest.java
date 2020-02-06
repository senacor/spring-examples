package com.senacor.tecco.ilms.katas.example.e02_method;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorHandlingMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void endpointReturnsCorrectStatusCode() throws Exception {
        this.mockMvc
                .perform(
                        get("/errorhandling/errorhandler")
                )
                .andExpect(status().isBadRequest());
    }

}