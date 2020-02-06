package com.senacor.tecco.ilms.katas.example.e03_global;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void numberFormatExceptionReturnsCorrectStatusCode() throws Exception {
        this.mockMvc
                .perform(
                        get("/errorhandling/global/thrownumberformatexception")
                )
                .andExpect(status().isExpectationFailed());
    }


    @Test
    void nullPointerExceptionReturnsCorrectStatusCode() throws Exception {
        this.mockMvc
                .perform(
                        get("/errorhandling/global/thrownullpointerexception")
                )
                .andExpect(status().isInternalServerError());
    }
}