package com.senacor.tecco.ilms.katas.example.e01_annotatedexception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnnotatedExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void annotatedExceptionReturnsCorrectStatusCode() throws Exception {
        this.mockMvc
                .perform(
                        get("/errorhandling/annotatedexception")
                )
                .andExpect(status().isNotFound());
    }


    @Test
    void plainExceptionReturnsCorrectStatusCode() throws Exception {
        assertThatThrownBy(
                () -> this.mockMvc
                        .perform(
                                get("/errorhandling/plainexception")
                        )
                        .andReturn()
        ).isInstanceOf(NestedServletException.class);
    }

}