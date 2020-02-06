package com.senacor.tecco.ilms.katas.testing.e01_junit4_vs_junit5;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestSyntaxUsingJunit4 {

    private static final Logger logger = LoggerFactory.getLogger(TestSyntaxUsingJunit4.class);

    private UserService userService = new UserService();

    /**
     * Will be executed before all tests, should be used for heavy tasks supposed to run only once
     */
    @BeforeClass
    public static void initialize() {
        logger.info(() -> "startup - creating DB connection");
    }

    /**
     * Will be executed before each test
     */
    @Before
    public void setUpUsers() {
        // Given
        userService.saveUser(new User("Jack", "Doe", "jackdoe@example.com"));
        userService.saveUser(new User("Brett", "Lee", "brettlee@example.com"));
        userService.saveUser(new User("Maria", "Liu", "marialiu@example.com"));
        userService.saveUser(new User("Shane", "Lee", "shanelee@example.com"));
        userService.saveUser(new User("Sophie", "West", "sophiewest@example.com"));
    }

    /**
     * Will be executed after all tests, should be used for heavy tasks supposed to run only once
     */
    @AfterClass
    public static void shutDown() {
        logger.info(() -> "shutdown - closing DB connection");
    }

    /**
     * Will be executed after each test
     */
    @After
    public void cleanUpDatabase() {
        userService.getAllUsers().stream().map(User::getUserId).forEach(userService::deleteUser);
    }

    /**
     * Junit comes with assertion methods
     */
    @Test
    public void saveUserAddsNewUserToExistingList() {
        // Given
        int countBefore = userService.countUsers();
        User user = userService.saveUser(new User("Peter", "Pan", "peterpan@example.com"));

        // When
        List<User> allUsers = userService.getAllUsers();

        // Then
        assertNotNull(allUsers);
        assertTrue(allUsers.contains(user));
        assertEquals(countBefore + 1, allUsers.size());
    }

    /**
     * Test will not be executed, due to @Ignore-Annotation
     */
    @Test
    @Ignore("TODO: needs some fixes before production use first")
    public void countUserReturnsCorrectValue() {
        // When
        int countUsers = userService.countUsers();

        // Then
        assertEquals(countUsers, userService.getAllUsers().size());
    }

    /**
     * Possible to manually fail a test
     */
    @Test
    public void manuallyFailingTest() {
        // When
        int count = userService.countUsers();

        // Then
        if (count > 0) {
            fail("Count should never be bigger than 0");
        }
    }

    /**
     * Tests can be run multiple times with different parameters by using the following class contruct (not necessarily
     * an inner class).
     * <p>
     * Class needs to be annotated with the following runner:
     */
    @RunWith(Parameterized.class)
    public static class ParameterizedTest {

        private UserService userService = new UserService();

        // For each parameter a field needs to be added
        private final User user;

        // Constructor to inject the fields
        public ParameterizedTest(User user) {
            this.user = user;
        }

        @Test
        public void nullableUserFieldsAccepted() {
            // When
            // Access the fields directly within the test
            User savedUser = userService.saveUser(user);

            // Then
            assertNotNull(savedUser);
            assertNotNull(savedUser.getUserId());
        }

        // Somehow define the parameters, multiple parameters can be passed using a list of arrays
        @Parameters
        public static List<User> getUsers() {
            return Arrays.asList(
                    new User(null, "Doe", "jackdoe@example.com"),
                    new User("Jack", null, "jackdoe@example.com"),
                    new User("Jack", "Doe", null)
            );
        }
    }
}
