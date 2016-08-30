package demo.fsl.mvc.e01_basics.e02_requestmapping;

import demo.fsl.Application;
import demo.fsl.mvc.e01_basics.BasicsConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by fsubasi on 20.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({Application.class, BasicsConfig.class})
@WebAppConfiguration
public class RequestMappingDemoControllerTest {

    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void requestMappingDemoGET_Test() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/requestMapping"))
                .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }

    @Test
    public void requestMappingDemoGETWithQueryParamsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/requestMapping/queryParams")
                .param("name", "john"))
                .andExpect(MockMvcResultMatchers.content().string("Hello john"));
    }

    @Test
    public void requestMappingDemoGETWithTemplateParamsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/requestMapping/templateParams/myParameter"))
                .andExpect(MockMvcResultMatchers.content().string("Parameter is myParameter"));
    }

    @Test
    public void requestMappingDemoGETWithTemplateParamsRegexTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/requestMapping/templateParams/regex/myParameter"))
                .andExpect(MockMvcResultMatchers.content().string("")); // should not return anything since regex rejects non-decimal
        mockMvc.perform(MockMvcRequestBuilders.get("/requestMapping/templateParams/regex/2"))
                .andExpect(MockMvcResultMatchers.content().string("Numerical parameter is 2"));
    }

    @Test
    public void requestMappingDemoPOST() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/requestMapping/form")
                .param("user", "user1")
                .param("email", "email@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.content()
                        .string("key: user, value: user1<br/>key: email, value: email@example.com<br/>"));
    }

    /**
     * Test with matrix vars, does not work right now can be Spring Mock Mvc problem
    @Test
    public void requestMappingDemoGETWithMatrixVariableTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(("/requestMapping/hotel/34;floor=45;room=3/guest")))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }*/

}
