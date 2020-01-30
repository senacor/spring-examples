package com.senacor.tecco.ilms.katas.views.e02_viewname;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@SpringBootTest
class ViewNameControllerTest {

    @Test
    void stringViewNameDemoTest() throws Exception {
        ViewNameController controller = new ViewNameController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/modelWithViewName"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewTemplate"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user", "email"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", "john"))
                .andExpect(MockMvcResultMatchers.model().attribute("email", "john@example.com"));
    }
}
