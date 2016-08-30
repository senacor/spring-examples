package demo.intern.mvc.e02_view.e01_modelAndView;

import demo.intern.Application;
import demo.intern.properties.e01_basics.TestConfiguration;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by fsubasi on 18.01.2016.
 */
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class, TestConfiguration.class})
public class ModelAndViewDemoControllerTest {
    @Test
    public void stringViewNameTest() throws Exception{
        ModelAndViewDemoController controller = new ModelAndViewDemoController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/views/modelAndView"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewDemo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user", "email"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", "john"))
                .andExpect(MockMvcResultMatchers.model().attribute("email", "john@example.com"));
    }
}
