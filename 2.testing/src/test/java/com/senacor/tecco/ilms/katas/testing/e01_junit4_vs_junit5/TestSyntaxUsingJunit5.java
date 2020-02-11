package com.senacor.tecco.ilms.katas.testing.e01_junit4_vs_junit5;

import com.senacor.tecco.ilms.katas.testing.model.User;
import com.senacor.tecco.ilms.katas.testing.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * public class and test methods not necessary anymore
 */
class TestSyntaxUsingJunit5 {

    private static final Logger logger = LoggerFactory.getLogger(TestSyntaxUsingJunit5.class);

    private UserService userService = new UserService();

    /**
     * Will be executed before all tests, should be used for heavy tasks supposed to run only once
     */
    @BeforeAll
    static void initialize() {
        logger.info(() -> "startup");
    }

    /**
     * Will be executed before each test
     */
    @BeforeEach
    void setUpUsers() {
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
    @AfterAll
    static void shutDown() {
        logger.info(() -> "shutdown");
    }

    /**
     * Will be executed after each test
     */
    @AfterEach
    void cleanUpDatabase() {
        userService.getAllUsers().stream().map(User::getUserId).forEach(userService::deleteUser);
    }

    /**
     * Junit comes with assertion methods
     */
    @Test
    void saveUserAddsNewUserToExistingList() {
        // Given
        int countBefore = userService.countUsers();

        // When
        User user = userService.saveUser(new User("Peter", "Pan", "peterpan@example.com"));

        // Then
        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertTrue(allUsers.contains(user));
        assertEquals(allUsers.size(), countBefore + 1);
    }

    /**
     * Test will not be executed, due to @Disabled-Annotation
     */
    @Test
    @Disabled("TODO: needs some fixes before production use first")
    void countUserReturnsCorrectValue() {
        // When
        int countUsers = userService.countUsers();

        // Then
        assertEquals(userService.getAllUsers().size(), countUsers);
    }

    /**
     * Possible to manually fail a test
     */
    @Test
    void manuallyFailingTest() {
        // When
        int count = userService.countUsers();

        // Then
        if (count > 0) {
            fail("Count should never be bigger than 0");
        }
    }

    /**
     * Parametrized test declaration has been simplified compared to JUnit 4.
     * Simply annotate method with @ParametrizedTest and add a parameter source.
     * Parameters are passed using method arguments.
     */
    @ParameterizedTest
    @MethodSource("getUsers")
    void nullableUserFieldsAccepted(User user) {
        // When
        User savedUser = userService.saveUser(user);

        // Then
        assertNotNull(savedUser);
        assertNotNull(savedUser.getUserId());
    }

    private static List<User> getUsers() {
        return Arrays.asList(
                new User(null, "Doe", "jackdoe@example.com"),
                new User("Jack", null, "jackdoe@example.com"),
                new User("Jack", "Doe", null)
        );
    }
}
