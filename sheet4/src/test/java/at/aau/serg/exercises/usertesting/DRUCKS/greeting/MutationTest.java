package at.aau.serg.exercises.usertesting.DRUCKS.greeting;

import at.aau.serg.exercises.usertesting.DRUCKS.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Angela Drucks, 12203559

public class MutationTest {
  private GreetingService greetingService;

  @BeforeEach
  void setUp() {
    // Use the real implementation of MailService
    greetingService = new GreetingService(new ConsoleMailService());
  }

  @Test
  void testGetAppropriateMessageForInactiveUser() {
    User inactiveUser = new User("inactive@example.com", "password");
    inactiveUser.setActive(false);

    String message = greetingService.getAppropriateMessage(inactiveUser);

    assertEquals(GreetingService.MSG_TEMPLATE_INACTIVE, message,
            "The appropriate message for an inactive user should match the predefined template.");
  }

  @Test
  void testGetAppropriateMessageForActiveUserNamedBob() {
    User activeBob = new User("bob@example.com", "password");
    activeBob.setActive(true);

    String message = greetingService.getAppropriateMessage(activeBob);

    assertEquals(GreetingService.MSG_NAMED_BOB, message,
            "The appropriate message for a user named 'bob' should match the special message.");
  }

  @Test
  void testGetAppropriateMessageForActiveUser() {
    User activeUser = new User("active@example.com", "password");
    activeUser.setActive(true);

    String message = greetingService.getAppropriateMessage(activeUser);

    assertEquals(GreetingService.MSG_TEMPLATE_ACTIVE, message,
            "The appropriate message for an active user should match the active user template.");
  }

  @Test
  void testGreetUserWithValidEmailSync() {
    User activeUser = new User("valid@example.com", "password");
    activeUser.setActive(true);

    // Ensure no exceptions and the greeting logic works
    greetingService.greetUser(activeUser, true, false);

    // Check the logic flow through console logging or debugging
  }

  @Test
  void testGreetUserWithInvalidEmail() {
    User activeUser = new User("invalid-email", "password");
    activeUser.setActive(true);

    // Ensure the greeting message is displayed in the console
    greetingService.greetUser(activeUser, true, false);

    // No exception should be thrown
  }

  @Test
  void testGreetUserAsync() throws InterruptedException {
    User activeUser = new User("async@example.com", "password");
    activeUser.setActive(true);

    // Run in async mode and wait for completion
    greetingService.greetUser(activeUser, true, true);

    // Ensure no exceptions and the async task completes
    Thread.sleep(100); // Simulate async delay
  }

  @Test
  void testGreetUserWithoutGreetingMessage() {
    User activeUser = new User("valid@example.com", "password");
    activeUser.setActive(true);

    // Ensure no exceptions when includeGreeting is false
    greetingService.greetUser(activeUser, false, false);

    // Check that the flow works without including a greeting
  }

  @Test
  void testEdgeCaseEmptyUsername() {
    User edgeCaseUser = new User("", "password");
    edgeCaseUser.setActive(true);

    // Ensure the flow doesn't break with an empty username
    assertDoesNotThrow(() -> greetingService.greetUser(edgeCaseUser, true, false));
  }

  @Test
  void testEdgeCaseNullUsername() {
    // Arrange: Create a user with a valid but edge-case username
    User edgeCaseUser = new User("", "password"); // Empty string as username
    edgeCaseUser.setActive(true);

    // Act & Assert: Ensure no exceptions are thrown
    assertDoesNotThrow(() -> {
      greetingService.greetUser(edgeCaseUser, true, false);
    }, "Greeting a user with an empty username should not throw an exception.");
  }

  @Test
  void testEdgeCaseUsernameWithSpecialCharacters() {
    User specialCharUser = new User("weird@chars!.com", "password");
    specialCharUser.setActive(true);

    // Ensure special characters in the username are handled
    greetingService.greetUser(specialCharUser, true, false);
  }
}