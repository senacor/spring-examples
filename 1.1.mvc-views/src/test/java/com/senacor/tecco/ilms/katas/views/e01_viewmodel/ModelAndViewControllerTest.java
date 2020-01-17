package com.senacor.tecco.ilms.katas.views.e01_viewmodel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by fsubasi on 18.01.2016.
 */
@WebAppConfiguration
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ModelAndViewControllerTest {

    @Test
    void stringViewNameTest() throws Exception {
        ModelAndViewController controller = new ModelAndViewController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/modelAndView"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewTemplate"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user", "email"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", "john"))
                .andExpect(MockMvcResultMatchers.model().attribute("email", "john@example.com"));
    }
}
