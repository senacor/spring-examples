package com.example.e03_requestmapping;

import com.Application;
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
@SpringApplicationConfiguration({Application.class})
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
    public void thatPathVariableIsMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mapping/pathVariable/myParameter"))
                .andExpect(MockMvcResultMatchers.content().string("Parameter is myParameter"));
    }

    @Test
    public void thatWrongPathVariableWithRegexIsNotMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mapping/pathVariableWithRegex/myParameter"))
                .andExpect(MockMvcResultMatchers.content().string("")); // should not return anything since regex rejects non-decimal
    }

    @Test
    public void thatPathVariableWithRegexIsMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mapping/pathVariableWithRegex/2"))
                .andExpect(MockMvcResultMatchers.content().string("Numerical parameter is 2"));
    }


    @Test
    public void thatQueryParamIsMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mapping/queryParam")
                .param("name", "john"))
                .andExpect(MockMvcResultMatchers.content().string("Hello john"));
    }

    @Test
    public void thatRequiredQueryParamIsMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mapping/requiredQueryParams")
                .param("name", "john"))
                .andExpect(MockMvcResultMatchers.content().string("Hello john"));
    }

    @Test
    public void thatMatrixVariableIsMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(("/mapping/hotel/34;floor=45;room=3/guest")))
                .andExpect(MockMvcResultMatchers.content().string("Hotel ID: 34<br/>Floor Number: 45<br/>Room Number: 3"));
    }

    @Test
    public void thatFormPOSTisMapped() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/mapping/form")
                .param("user", "user1")
                .param("email", "email@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.content()
                        .string("key: user, value: user1<br/>key: email, value: email@example.com<br/>"));
    }

}
