package com.senacor.tecco.ilms.katas.example.e01_helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class HelloWorldRestControllerTest {
	
    @Test
    void helloWorldRestTest() throws Exception{
        HelloWorldRestController controller = new HelloWorldRestController();
        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/basics/helloWorldRest"))
                .andExpect(MockMvcResultMatchers.content().string("Hello world!"));
    }
}
