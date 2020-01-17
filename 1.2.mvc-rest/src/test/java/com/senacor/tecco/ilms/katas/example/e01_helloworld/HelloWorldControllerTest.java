package com.senacor.tecco.ilms.katas.example.e01_helloworld;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by fsubasi on 20.01.2016.
 */
@ExtendWith(SpringExtension.class)
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
