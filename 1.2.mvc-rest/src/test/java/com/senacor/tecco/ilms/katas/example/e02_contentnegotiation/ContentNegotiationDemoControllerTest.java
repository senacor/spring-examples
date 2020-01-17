package com.senacor.tecco.ilms.katas.example.e02_contentnegotiation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by fsubasi on 20.01.2016.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContentNegotiationDemoControllerTest {

    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    void thatUserIsReturnedAsJSON() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/negotiation/user.json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":0,\"firstName\":\"Michael\",\"lastName\":\"Menzel\",\"email\":\"michael.menzel@senacor.com\"}"));
    }

    @Test
    void thatUserIsReturnedAsXML() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/negotiation/userAsXML"))
                .andExpect(MockMvcResultMatchers.content().string("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><email>michael.menzel@senacor.com</email><firstName>Michael</firstName><lastName>Menzel</lastName><userId>0</userId></user>"));
    }


}
