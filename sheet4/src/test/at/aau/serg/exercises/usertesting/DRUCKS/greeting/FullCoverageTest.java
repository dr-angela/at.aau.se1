package at.aau.serg.exercises.usertesting.DRUCKS.greeting;

import at.aau.serg.exercises.usertesting.DRUCKS.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Angela Drucks, 12203559

public class FullCoverageTest {
  private GreetingService greetingService;

  @BeforeEach
  void setUp() {
    greetingService = new GreetingService(new ConsoleMailService());
  }

  @Test
  void testGreetUserValidEmailSync() {
    User user = new User("valid@example.com", "password");
    user.setActive(true);

    greetingService.greetUser(user, true, false);

    // Check console output manually if needed
  }

  @Test
  void testGreetUserInvalidEmail() {
    User user = new User("invalid-email", "password");
    user.setActive(true);

    greetingService.greetUser(user, true, false);

    // Since the email is invalid, the message will be printed to the console
  }

  @Test
  void testGreetUserAsync() throws InterruptedException {
    User user = new User("async@example.com", "password");
    user.setActive(true);

    greetingService.greetUser(user, true, true);

    // Allow async task to complete
    Thread.sleep(100);
  }
}