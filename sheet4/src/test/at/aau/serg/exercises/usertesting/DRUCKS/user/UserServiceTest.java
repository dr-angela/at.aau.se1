package at.aau.serg.exercises.usertesting.DRUCKS.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

// Angela Drucks, 12203559
public class UserServiceTest {

    private UserService userService; // Field for the UserService instance

    // 4.1 - Set up the test environment
    @BeforeEach
    void setUp() {
        // Initialize UserService with a dummy PasswordEncoder
        userService = new InMemoryUserService(new org.springframework.security.crypto.password.PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString(); // Simple pass-through for testing
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword); // Direct comparison for testing
            }
        });
    }

    // 4.1.1 - Register a user that is not activated (parameterized test)
    @ParameterizedTest
    @CsvSource({
            "test1@example.com, password123",
            "user2@example.com, pass456",
            "hello@domain.com, strongpass789"
    })
    void testRegisterCreatesUserButNotActivatedParameterized(String email, String password) {
        // Arrange & Act: Register the user
        User user = userService.register(email, password);

        // Assert: Check if user is created but not activated
        assertNotNull(user, "User should be created");
        assertFalse(user.isActivated(), "User should not be activated after registration");
    }

    // 4.1.2 - Verify that registered users can log in
    @Test
    void testRegisteredUserCanLogin() {
        // Arrange: Register and activate a user
        userService.register("test@example.com", "password123");
        userService.activateUser("test@example.com");

        // Act: Attempt to log in with correct credentials
        boolean loginSuccessful = userService.login("test@example.com", "password123");

        // Assert: Login should be successful
        assertTrue(loginSuccessful); // Verify that the login is successful
    }

    // 4.1.3 - Verify password reset functionality (step-by-step approach)

    @Test
    void testResetPasswordUpdatesUserPassword() {
        // Arrange
        User user = userService.register("test@example.com", "oldPassword123");
        userService.activateUser("test@example.com");

        // Act
        userService.resetPassword("test@example.com", "newPassword123");

        // Assert
        assertNotNull(user);
        assertEquals("newPassword123", user.getPassword(), "Password should be updated after reset");
    }

    @Test
    void testLoginFailsWithOldPasswordAfterReset() {
        // Arrange
        userService.register("test@example.com", "oldPassword123");
        userService.activateUser("test@example.com");

        // Act
        userService.resetPassword("test@example.com", "newPassword123");

        // Assert
        assertFalse(userService.login("test@example.com", "oldPassword123"),
                "Login with the old password should fail after a reset");
    }

    @Test
    void testLoginSucceedsWithNewPasswordAfterReset() {
        // Arrange
        userService.register("test@example.com", "oldPassword123");
        userService.activateUser("test@example.com");

        // Act
        userService.resetPassword("test@example.com", "newPassword123");

        // Assert
        assertTrue(userService.login("test@example.com", "newPassword123"),
                "Login with the new password should succeed after a reset");
    }

    // 4.1.4 - Verify user activation functionality
    @Test
    void testUserIsActivatedAfterActivation() {
        // Arrange: Register a user
        User user = userService.register("test@example.com", "password123");

        // Act: Activate the user
        userService.activateUser("test@example.com");

        // Assert: Verify the user is activated
        assertTrue(user.isActivated(), "User should be activated after calling activateUser");
    }

    // 4.1.5 - Verify exceptions for invalid inputs or states

    @Test
    void testRegisterThrowsExceptionForInvalidUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register(null, "password123"));
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("", "password123"));
    }

    @Test
    void testRegisterThrowsExceptionForInvalidPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("test@example.com", null));
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("test@example.com", ""));
    }

    @Test
    void testRegisterThrowsExceptionForDuplicateUsername() {
        userService.register("test@example.com", "password123");
        assertThrows(IllegalStateException.class,
                () -> userService.register("test@example.com", "password123"));
    }

    @Test
    void testActivateThrowsExceptionForNonExistentUser() {
        assertThrows(IllegalStateException.class,
                () -> userService.activateUser("nonexistent@example.com"));
    }
}