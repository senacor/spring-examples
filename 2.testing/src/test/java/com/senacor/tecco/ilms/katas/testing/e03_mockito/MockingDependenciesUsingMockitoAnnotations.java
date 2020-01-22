package com.senacor.tecco.ilms.katas.testing.e03_mockito;

import com.senacor.tecco.ilms.katas.testing.web.rest.UserController;
import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Using the Mockito Extension in JUnit5 enables creation and injection of mocks using annotations. The same behavior
 * can be reached in JUnit4 using the MockitoJUnitRunner
 */
@ExtendWith(MockitoExtension.class)
class MockingDependenciesUsingMockitoAnnotations {

    // create a mock corresponding to mock(UserService.class)
    @Mock
    private UserService userService;
    // create an instance of this object and inject all created mocks (order of the fields is irrelevant for the process)
    @InjectMocks
    private UserController userController;

    // create an argument captor corresponding to ArgumentCaptor.forClass(Integer.class)
    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;

    /**
     * Usage of mocks is similar to the example without annotation processing shown before
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

        verify(userService, times(2)).getUserFromID(integerArgumentCaptor.capture());

        Integer latestValue = integerArgumentCaptor.getValue();
        List<Integer> allValues = integerArgumentCaptor.getAllValues();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(latestValue).isEqualTo(6);
            softly.assertThat(allValues).containsExactly(5, 6);
        });
    }
}
