package com.senacor.tecco.ilms.katas.testing.e02_extended_assertions;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.senacor.tecco.ilms.katas.testing.service.UserService.UID_CANNOT_BE_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * AssertJ introduces much more powerful assertion implementations, improving the handling and debugging with extended
 * error logs, compared to the standard JUnit assertions.
 */
class AssertJSyntax {

    private UserService userService = new UserService(null);

    @BeforeEach
    void setUpUsers() {
        // Given
        userService.saveUser(new User("Jack", "Doe", "jackdoe@example.com"));
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
     * Plain JUnit Assertions only offer a limited number of assertions, which does not provide a helpful error
     * description for every use case.
     */
    @Test
    void jUnitAssertion_assertEquals() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        assertEquals(6, allUsers.size());
    }

    @Test
    void jUnitAssertion_assertTrue() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        assertTrue(allUsers.contains(newUser));
    }

    @Test
    void jUnitAssertions_checkingExceptions() {
        // Then, When
        assertThrows(IllegalArgumentException.class, () -> userService.getUserFromID(null));
    }

    /**
     * Instead of generic assertions, assertJ uses method overloading to create specific assertions for different
     * objects, allowing much more concise result checks and improvements of the description in case of an error.
     */
    @Test
    void assertJAssertions_hasSize() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        assertThat(allUsers).hasSize(6);
    }

    @Test
    void assertJAssertions_contains() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        assertThat(allUsers).contains(newUser);
    }

    /**
     * Multiple assertions on the same object can be concatenated, assertion stops on first error
     */
    @Test
    void assertJAssertions_concatenated() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        assertThat(allUsers).hasSize(5).doesNotContain(newUser);
    }

    @Test
    void assertJAssertions_checkingExceptions() {
        // When, Then
        assertThatThrownBy(() -> userService.getUserFromID(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UID_CANNOT_BE_NULL);
    }

    @Test
    void assertJAssertions_checkingExceptions_BestPractice() {
        // When
        Throwable throwable = catchThrowable(() -> userService.getUserFromID(null));

        // Then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UID_CANNOT_BE_NULL);
    }

    @Test
    void assertJAssertions_explicitlyNoException() {
        // When, Then
        assertThatCode(() -> userService.getUserFromID(5))
                .doesNotThrowAnyException();
    }

    /**
     * Code completions offered by the IDE makes it easier to find the correct assertion for specific use cases.
     */
    @Test
    void assertJAssertions_examples() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        assertThat(newUser).isEqualToComparingFieldByField(new User("Peter", "Pan", "peterpan@example.com"));
        assertThat(Optional.of(newUser)).hasValueSatisfying(user -> assertThat(user.getUserId()).isNull());
        assertThat(allUsers)
                .extracting("name")
                .containsExactlyInAnyOrder("Jack", "Brett", "Maria", "Shane", "Sophie");
    }

    /**
     * Classic use of assertions fails test as soon as first assertion fails. To find all failed assertions at once,
     * soft assertions can be used.
     */
    @Test
    void softAssertions() {
        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(allUsers).hasSize(6);
        softAssertions.assertThat(allUsers).contains(newUser);
        softAssertions.assertAll();

        // Lambda style (automatic assertAll)
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(allUsers).hasSize(6);
            softly.assertThat(allUsers).contains(newUser);
        });
    }

    @Test
    void softAssertions_nullableCheck_Failure() {
        // When
        // Beware of null pointer exceptions
        User nullable = userService.getUserFromID(Integer.MAX_VALUE);

        // Then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(nullable).isNotNull();
            softly.assertThat(nullable.getUserId()).isEqualTo(Integer.MAX_VALUE);
            softly.assertThat(nullable.getEmail()).isEqualTo("my@expected.mail");
        });
    }

    @Test
    void softAssertions_nullableCheck_BestPractice() {
        // When
        User nullable = userService.getUserFromID(Integer.MAX_VALUE);

        // Then
        // Check outside of soft assertion, failing before further checks
        assertThat(nullable).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(nullable.getUserId()).isEqualTo(Integer.MAX_VALUE);
            softly.assertThat(nullable.getEmail()).isEqualTo("my@expected.mail");
        });

    }
}
