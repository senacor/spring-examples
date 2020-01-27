package com.senacor.tecco.ilms.katas.testing.e04_spring_integration;

import com.senacor.tecco.ilms.katas.testing.TestingApplication;
import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.response.UserResponse;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import com.senacor.tecco.ilms.katas.testing.web.rest.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Using @SpringBootTest the whole application with its context is started and dependencies are injected using spring
 * dependency injection.
 */
@SpringBootTest(classes = TestingApplication.class)
class SpringIntegrationMockBeans {

    // Autowire services that need to be accessed during the tests
    @Autowired
    private UserController userController;
    // Spring beans can also be mocked and will be injected into spring context automatically
    @MockBean
    private UserService userService;

    @Test
    void test() {
        // Given
        User mockUser = new User("Jack", "Doe", "jackdoe@example.com");
        when(userService.getUserFromID(anyInt())).thenReturn(mockUser);

        // When
        UserResponse userResponse = userController.getUser(5);

        // Then
        assertThat(userResponse.getUser()).isEqualTo(mockUser);
    }
}
