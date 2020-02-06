package com.senacor.tecco.ilms.katas.testing.e03_mockito;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.response.UserResponse;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import com.senacor.tecco.ilms.katas.testing.web.rest.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * In contrast to mocks, spies can be primarily used to check the communication with dependencies using the actual
 * implementation of dependent services
 */
@ExtendWith(MockitoExtension.class)
public class SpyingDependencies {

    // create a spy corresponding to spy(UserService.class)
    @Spy
    private UserService userService;
    // create an instance of this object and inject all created mocks (order of the fields is irrelevant for the process)
    @InjectMocks
    private UserController userController;

    @Test
    void spyMethods() {
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        UserResponse userResponse = userController.createUser(newUser);

        verify(userService).saveUser(eq(newUser));

        assertThat(userResponse.getUser()).isEqualTo(newUser);
        assertThat(userResponse.getUser().getUserId()).isNotNull();
    }

    /**
     * Method calls on a spy can also be mocked, preventing the call of the actual implementation
     */
    @Test
    void mockMethodsOfASpy() {
        // Given
        User mockedUser = new User("Peter", "Pan", "peterpan@example.com");
        // Syntax slightly defers from definition of method calls on mocks
        doReturn(mockedUser).when(userService).saveUser(any(User.class));

        // When
        UserResponse userResponse = userController.createUser(mockedUser);

        // Then
        verify(userService).saveUser(eq(mockedUser));

        assertThat(userResponse.getUser()).isEqualTo(mockedUser);
        // Creation of ID done in actual implementation is never called
        assertThat(userResponse.getUser().getUserId()).isNull();
    }

}
