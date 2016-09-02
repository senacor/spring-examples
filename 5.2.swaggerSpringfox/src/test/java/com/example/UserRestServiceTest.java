package com.example;

/**
 * Created by asiddiqui on 30/03/16.
 *
 * This test class comprises of tests for the UserRestService class
 *
 */

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserRestServiceTest {


    // We specify where the generated snippets will be stored
    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    private RestDocumentationResultHandler document;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.document = MockMvcRestDocumentation
                //  {method-name} => The generated snippets will be kept under a folder with the test
                // method's name i.e. the folder name will be 'get-user' for the getUser method below,
                // the generated snippet location is later specified in the .asciidoc file
                .document("{method-name}",
                        // pretty print json or xml
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation)
                        .uris()
                        // Specify service URI so that http requests can be documented correctly
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8080))
                .build();
    }

    /**********************************************************************
     *
     * GET
     *
     *********************************************************************/

    @Test
    public void getUserByLastName() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON)
                .param("lastName", "Lee"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUserById() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @Test
    public void saveUser() throws Exception{
        String jsonContent = "{\"userId\" : 102, \"firstName\" : \"Elton\", " +
                "\"lastName\" : \"John\", \"email\" : \"EltonJohn@example.com\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/


    @Test
    public void putUser() throws Exception{
        String jsonContent = "{\"userId\" : 2, \"firstName\" : \"Elton\", " +
                "\"lastName\" : \"John\", \"email\" : \"JohnElton@example.com\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/

    @Test
    public void deleteUser() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}