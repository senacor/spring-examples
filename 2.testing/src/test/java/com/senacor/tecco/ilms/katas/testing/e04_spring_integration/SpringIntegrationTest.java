package com.senacor.tecco.ilms.katas.testing.e04_spring_integration;

import com.senacor.tecco.ilms.katas.testing.TestingApplication;
import com.senacor.tecco.ilms.katas.testing.web.rest.UserController;
import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.response.UserResponse;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Using @SpringBootTest the whole application with its context is started and dependencies are injected using spring
 * dependency injection.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestingApplication.class)
class SpringIntegrationTest {

    // Autowire services that need to be accessed during the tests
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    private User userToFind;

    @BeforeEach
    void setUpUsers() {
        userToFind = userService.saveUser(new User("Jack", "Doe", "jackdoe@example.com"));
        userService.saveUser(new User("Brett", "Lee", "brettlee@example.com"));
        userService.saveUser(new User("Maria", "Liu", "marialiu@example.com"));
        userService.saveUser(new User("Shane", "Lee", "shanelee@example.com"));
        userService.saveUser(new User("Sophie", "West", "sophiewest@example.com"));
    }

    @Test
    void test() {
        UserResponse userResponse = userController.getUser(userToFind.getUserId());

        assertThat(userResponse.getUser()).isEqualTo(userToFind);
    }
}
