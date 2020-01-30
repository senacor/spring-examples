package com.senacor.tecco.ilms.katas.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This test class comprises of tests for the UserRestService class
 * <p>
 * In this project, this calss is of vital importance as it is where the code snippets
 * for Spring REST docs document are created.
 */
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
class UserRestServiceTest {

    private RestDocumentationResultHandler document;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.document = MockMvcRestDocumentation
                //  {method-name} => The generated snippets will be kept under a folder with the test
                // method's name i.e. the folder name will be 'get-user' for the getUser method below,
                // the generated snippet location is later specified in the .asciidoc file
                .document("{method-name}",
                        // pretty print json or xml
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
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
    void getUserByLastName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON)
                .param("lastName", "Lee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document
                        .document(PayloadDocumentation.responseFields( // We are documenting the response
                                PayloadDocumentation.fieldWithPath("[].userId").description("Generated user Id"),
                                PayloadDocumentation.fieldWithPath("[].firstName").description("User's first name"),
                                PayloadDocumentation.fieldWithPath("[].lastName").description("User's last name"),
                                PayloadDocumentation.fieldWithPath("[].email").description("User's email address")
                        )));
    }

    @Test
    void getUserById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document
                        .document(PayloadDocumentation.responseFields( // We are documenting the response
                                PayloadDocumentation.fieldWithPath("userId").description("Generated user Id"),
                                PayloadDocumentation.fieldWithPath("firstName").description("User's first name"),
                                PayloadDocumentation.fieldWithPath("lastName").description("User's last name"),
                                PayloadDocumentation.fieldWithPath("email").description("User's email address")
                        )));
    }


    /**********************************************************************
     *
     * POST
     *
     *********************************************************************/

    @Test
    void consumeUser() throws Exception {
        String jsonContent = "{\"userId\" : 101, \"firstName\" : \"Max\", " +
                "\"lastName\" : \"Mustermann\", \"email\" : \"MaxMustermann@example.com\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document.document());
    }

    @Test
    void saveUser() throws Exception {
        String jsonContent = "{\"userId\" : 102, \"firstName\" : \"Elton\", " +
                "\"lastName\" : \"John\", \"email\" : \"EltonJohn@example.com\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/saveUser")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andDo(document
                        .document(PayloadDocumentation.responseFields( // We are documenting the response
                                PayloadDocumentation.fieldWithPath("_successful").description("Request status"),
                                PayloadDocumentation.fieldWithPath("_messages").description("Request messages"),
                                PayloadDocumentation.fieldWithPath("user.userId").description("Generated user Id"),
                                PayloadDocumentation.fieldWithPath("user.firstName").description("User's first name"),
                                PayloadDocumentation.fieldWithPath("user.lastName").description("User's last name"),
                                PayloadDocumentation.fieldWithPath("user.email").description("User's email address")
                        )));
    }

    /**********************************************************************
     *
     * PUT
     *
     *********************************************************************/


    @Test
    void putUser() throws Exception {
        String jsonContent = "{\"userId\" : 2, \"firstName\" : \"Elton\", " +
                "\"lastName\" : \"John\", \"email\" : \"JohnElton@example.com\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document.document());
    }


    /**********************************************************************
     *
     * Delete
     *
     *********************************************************************/

    @Test
    void deleteUser() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document.document());
    }

}
