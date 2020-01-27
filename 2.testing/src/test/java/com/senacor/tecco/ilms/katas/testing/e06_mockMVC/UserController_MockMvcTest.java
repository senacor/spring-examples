package com.senacor.tecco.ilms.katas.testing.e06_mockMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MockMVC Tests can be used to start up the application under test and create http requests against the exposed
 * controllers. The responses can be checked using numerous response matchers.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserController_MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    private User userToFind;

    @BeforeEach
    void setUpUsers() {
        // Given
        userToFind = userService.saveUser(new User("Jack", "Doe", "jackdoe@example.com"));
        userService.saveUser(new User("Brett", "Lee", "brettlee@example.com"));
        userService.saveUser(new User("Maria", "Liu", "marialiu@example.com"));
        userService.saveUser(new User("Shane", "Lee", "shanelee@example.com"));
        userService.saveUser(new User("Sophie", "West", "sophiewest@example.com"));
    }

    @AfterEach
    void cleanUpDatabase() {
        userService.getAllUsers().stream().map(User::getUserId).forEach(userService::deleteUser);
    }

    /**
     * Check, that the response code is the expected
     */
    @Test
    void shouldReturnCorrectHttpStatus() throws Exception {
        this.mockMvc
                .perform(
                        get("/users/" + userToFind.getUserId())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Check, that the response code is the expected
     */
    @Test
    void shouldNotFindInvalidEndpoint() throws Exception {
        this.mockMvc
                .perform(
                        get("/thisEndpointDoesntExist")
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Check the serialized JSON Response using jsonPath matcher
     */
    @Test
    void shouldReturnCorrectUserEntityAsJson() throws Exception {
        this.mockMvc
                .perform(
                        get("/users/" + userToFind.getUserId())
                )
                .andDo(print())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.firstName").value(userToFind.getFirstName()));
    }

    /**
     * Different REST calls supported
     */
    @Test
    void addRequestBody() throws Exception {
        String newFirstName = "Juan";
        this.mockMvc
                .perform(
                        put("/users/" + userToFind.getUserId())
                                .content(
                                        objectMapper.writeValueAsString(
                                                new User(newFirstName, "Doe", "jackdoe@example.com")
                                        )
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.userId").value(userToFind.getUserId()))
                .andExpect(jsonPath("$.user.firstName").value(newFirstName));
    }
}
