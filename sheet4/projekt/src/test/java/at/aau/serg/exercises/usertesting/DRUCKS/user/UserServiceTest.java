package at.aau.serg.exercises.usertesting.DRUCKS.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    // 4.1.1. Register a user that is not activated
    @Test
    void testRegisterCreatesUserButNotActivated() {
        User user = userService.register("test@example.com", "password123");

        // Assert: User is created but not activated
        assertNotNull(user); // Check if the user is created
        assertFalse(user.isActivated()); // Verify the user is not activated
    }

    // 4.1.2. Verify that registered users can log in
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

    // 4.1.3. Verify password reset functionality
    @Test
    void testPasswordResetAllowsNewPasswordForLogin() {
        // Arrange: Register and activate a user
        userService.register("test@example.com", "oldPassword123");
        userService.activateUser("test@example.com");

        // Act: Reset the user's password (assuming resetPassword is implemented)
        userService.resetPassword("test@example.com", "newPassword123");

        // Assert:
        // Login with the old password should not work
        assertFalse(userService.login("test@example.com", "oldPassword123"),
                "Login with the old password should fail");

        // Login with the new password should work
        assertTrue(userService.login("test@example.com", "newPassword123"),
                "Login with the new password should succeed");
    }

}