package com.senacor.tecco.ilms.katas.testing.e03_mockito;

import com.senacor.tecco.ilms.katas.testing.web.rest.UserController;
import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.response.UserResponse;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Dependencies of services can be mocked and thus, return values when calling methods defined for a specific input,
 * without actually calling the real method implementation.
 * Argument captors can be used to capture and check arguments passed to methods of a mock.
 */
public class MockingDependencies {

    private UserService userService;
    private UserController userController;

    private ArgumentCaptor<Integer> integerArgumentCaptor;

    /**
     * Set up the mocks and argument captors
     */
    @BeforeEach
    void setUpMocks() {
        userService = mock(UserService.class);

        userController = new UserController(userService);

        integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    /**
     * Define return value of a mocked method without any constraint on the method argument
     */
    @Test
    void mockMethods() {
        User mockedUser = new User("Peter", "Pan", "peterpan@example.com");
        when(userService.getUserFromID(anyInt())).thenReturn(mockedUser);

        UserResponse user = userController.getUser(5);
        assertThat(user.getUser()).isEqualTo(mockedUser);
    }

    /**
     * Resetting mock removes all previous definitions
     */
    @Test
    void resetMock() {
        User mockedUser = new User("Peter", "Pan", "peterpan@example.com");
        when(userService.getUserFromID(anyInt())).thenReturn(mockedUser);

        UserResponse user = userController.getUser(5);
        assertThat(user.getUser()).isEqualTo(mockedUser);

        reset(userService);

        user = userController.getUser(5);
        assertThat(user.getUser()).isNull();

    }

    /**
     * Define return value of a mocked method only for specific method arguments using arguments matchers
     */
    @Test
    void usingMatchers() {
        User mockedUser = new User("Peter", "Pan", "peterpan@example.com");
        when(userService.getUserFromID(eq(5))).thenReturn(mockedUser);

        UserResponse user5 = userController.getUser(5);
        assertThat(user5.getUser()).isEqualTo(mockedUser);

        UserResponse user6 = userController.getUser(6);
        assertThat(user6.getUser()).isNull();

        reset(userService);
        when(userService.getUserFromID(gt(5))).thenReturn(mockedUser);

        user5 = userController.getUser(5);
        assertThat(user5.getUser()).isNull();

        user6 = userController.getUser(6);
        assertThat(user6.getUser()).isEqualTo(mockedUser);

        UserResponse user7 = userController.getUser(7);
        assertThat(user7.getUser()).isEqualTo(mockedUser);

    }

    /**
     * Return values of mocked methods can be build depending on the used method arguments using answers
     */
    @Test
    void returnValuesDependingOnInput() {
        when(userService.getUserFromID(anyInt())).thenAnswer(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            Integer id = (Integer) arguments[0];

            User user = new User("Peter", "Pan", "peterpan@example.com");
            user.setUserId(id);

            return user;
        });

        UserResponse user5 = userController.getUser(5);
        UserResponse user6 = userController.getUser(6);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(user5.getUser()).isNotNull();
            softly.assertThat(user6.getUser()).isNotNull();
        });

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(user5.getUser().getUserId()).isEqualTo(5);
            softly.assertThat(user6.getUser().getUserId()).isEqualTo(6);
        });
    }

    /**
     * Verify can be used to check how often a method of a mocked object has been called
     */
    @Test
    void verifyingActualMessageCalls() {
        User mockedUser = new User("Peter", "Pan", "peterpan@example.com");
        when(userService.getUserFromID(anyInt())).thenReturn(mockedUser);

        userController.getUser(5);

        // method has been called once with argument "5"
        verify(userService, times(1)).getUserFromID(eq(5));
        // method has been called once (default value if not specified) with any argument
        verify(userService).getUserFromID(anyInt());
        // method has never been called with argument "6"
        verify(userService, never()).getUserFromID(eq(6));
        // all other methods not specified in a verify statement have never been called
        verifyNoMoreInteractions(userService);
    }

    /**
     * Argument Captors record method arguments to enable later checks
     */
    @Test
    void capturingMethodArguments() {
        when(userService.getUserFromID(anyInt())).thenAnswer(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            Integer id = (Integer) arguments[0];

            User user = new User("Peter", "Pan", "peterpan@example.com");
            user.setUserId(id);

            return user;
        });

        userController.getUser(5);
        userController.getUser(6);

        // verify the method is called 2 times with any Integer value as argument
        verify(userService, times(2)).getUserFromID(integerArgumentCaptor.capture());

        // return the latest value of the argument used
        Integer latestValue = integerArgumentCaptor.getValue();
        // return all values of the argument used
        List<Integer> allValues = integerArgumentCaptor.getAllValues();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(latestValue).isEqualTo(6);
            softly.assertThat(allValues).containsExactly(5, 6);
        });
    }
}
