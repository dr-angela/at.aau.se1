package at.aau.serg.exercises.usertesting.DRUCKS.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    private UserService userService; // Field for the UserService instance

    @BeforeEach
    void setUp() {
        // Initialize UserService with a dummy PasswordEncoder
        userService = new InMemoryUserService(new org.springframework.security.crypto.password.PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString(); // Simple pass through for testing
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
}