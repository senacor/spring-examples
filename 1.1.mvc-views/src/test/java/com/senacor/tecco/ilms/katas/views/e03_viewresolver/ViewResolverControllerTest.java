package com.senacor.tecco.ilms.katas.views.e03_viewresolver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@SpringBootTest
class ViewResolverControllerTest {

    @Test
    void viewResolverDemoTest() throws Exception {
        ViewResolverController controller = new ViewResolverController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/viewResolver/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewResolverTemplate"));
                /*
                .andExpect(MockMvcResultMatchers.content().string(
                        CoreMatchers.containsString("<p>First Name: <span>John</span></p>")));*/
    }
}
