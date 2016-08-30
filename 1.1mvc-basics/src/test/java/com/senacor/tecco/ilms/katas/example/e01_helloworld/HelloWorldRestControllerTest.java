package com.senacor.tecco.ilms.katas.example.e01_helloworld;

import com.senacor.tecco.ilms.katas.Application;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by fsubasi on 24.01.2016.
 */
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class HelloWorldRestControllerTest {
	
    @Test
    public void helloWorldRestTest() throws Exception{
        HelloWorldRestController controller = new HelloWorldRestController();
        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/basics/helloWorldRest"))
                .andExpect(MockMvcResultMatchers.content().string("Hello world!"));
    }
}
