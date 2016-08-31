package com.senacor.tecco.ilms.katas.views.e03_viewresolver;

import com.senacor.tecco.ilms.katas.views.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by fsubasi on 02.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class})
public class ViewResolverControllerTest {

    @Test
    public void viewResolverDemoTest() throws Exception{
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
