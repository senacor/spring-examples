package com.senacor.tecco.ilms.katas.testing.e02_extended_assertions;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * AssertJ introduces much more powerful assertion implementations, improving the handling and debugging with extended
 * error logs, compared to the standard JUnit assertions.
 */
class AssertJSyntax {

    private UserService userService = new UserService(null);

    @BeforeEach
    void setUpUsers() {
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
     * AssertJ introduces different assertions, making both, the assertion and error message more readable
     */
    @Test
    void assertJ() {
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        List<User> allUsers = userService.getAllUsers();

        // Plain JUnit Assertions:

        // java.lang.AssertionError: expected:<6> but was:<5>
        assertEquals(6, allUsers.size());
        // java.lang.AssertionError + Stack Trace
        assertTrue(allUsers.contains(newUser));

        // AssertJ Assertions:

        // OUTPUT:
        // java.lang.AssertionError:
        // Expected size:<6> but was:<5> in:
        // <[User{userId=1, firstName='Jack', lastName='Doe', email='jackdoe@example.com'},
        //    User{userId=2, firstName='Brett', lastName='Lee', email='brettlee@example.com'},
        //    User{userId=3, firstName='Maria', lastName='Liu', email='marialiu@example.com'},
        //    User{userId=4, firstName='Shane', lastName='Lee', email='shanelee@example.com'},
        //    User{userId=5, firstName='Sophie', lastName='West', email='sophiewest@example.com'}]>
        assertThat(allUsers).hasSize(6);
        // OUTPUT:
        //java.lang.AssertionError:
        // Expecting:
        //  <[User{userId=1, firstName='Jack', lastName='Doe', email='jackdoe@example.com'},
        //    User{userId=2, firstName='Brett', lastName='Lee', email='brettlee@example.com'},
        //    User{userId=3, firstName='Maria', lastName='Liu', email='marialiu@example.com'},
        //    User{userId=4, firstName='Shane', lastName='Lee', email='shanelee@example.com'},
        //    User{userId=5, firstName='Sophie', lastName='West', email='sophiewest@example.com'}]>
        // to contain:
        //  <[User{userId=0, firstName='Peter', lastName='Pan', email='peterpan@example.com'}]>
        // but could not find:
        //  <[User{userId=0, firstName='Peter', lastName='Pan', email='peterpan@example.com'}]>
        assertThat(allUsers).contains(newUser);
        // concatenation possible
        assertThat(allUsers).hasSize(5).doesNotContain(newUser);

        assertThat(newUser).isEqualToComparingFieldByField(new User("Peter", "Pan", "peterpan@example.com"));
        assertThat(Optional.of(newUser)).hasValueSatisfying(user -> assertThat(user.getUserId()).isNull());
    }

    /**
     * Classic use of assertions fails test as soon as first assertion fails. To find all failed assertions at once,
     * soft assertions can be used.
     */
    @Test
    void softAssertions() {
        User newUser = new User("Peter", "Pan", "peterpan@example.com");
        List<User> allUsers = userService.getAllUsers();

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
        // Beware of null pointer exceptions
        User nullable = userService.getUserFromID(Integer.MAX_VALUE);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(nullable).isNotNull();
            softly.assertThat(nullable.getUserId()).isEqualTo(Integer.MAX_VALUE);
            softly.assertThat(nullable.getEmail()).isEqualTo("my@expected.mail");
        });
    }

    @Test
    void softAssertions_nullableCheck_BestPractice() {
        User nullable = userService.getUserFromID(Integer.MAX_VALUE);

        // Check outside of soft assertion, failing before further checks
        assertThat(nullable).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(nullable.getUserId()).isEqualTo(Integer.MAX_VALUE);
            softly.assertThat(nullable.getEmail()).isEqualTo("my@expected.mail");
        });

    }
}
