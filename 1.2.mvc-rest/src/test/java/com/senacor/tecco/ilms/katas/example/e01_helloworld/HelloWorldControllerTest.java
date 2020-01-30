package com.senacor.tecco.ilms.katas.example.e01_helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class HelloWorldControllerTest {

    @Test
    void helloWorldTest() throws Exception{
        HelloWorldController controller = new HelloWorldController();
        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/basics/helloWorld"))
                .andExpect(MockMvcResultMatchers.content().string("Hello world!"));
    }
}
