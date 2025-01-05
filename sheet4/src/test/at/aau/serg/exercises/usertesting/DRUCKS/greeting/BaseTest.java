package at.aau.serg.exercises.usertesting.DRUCKS.greeting;

import at.aau.serg.exercises.usertesting.DRUCKS.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Angela Drucks, 12203559

public class BaseTest {
  private GreetingService greetingService;

  @BeforeEach
  void setUp() {
    greetingService = new GreetingService(new ConsoleMailService());
  }

  @Test
  void testGreetInactiveUser() {
    User user = new User("inactive@example.com", "password");
    user.setActive(false);

    String message = greetingService.getAppropriateMessage(user);

    assertEquals("Your account is not activated yet.", message);
  }

  @Test
  void testGreetActiveUser() {
    User user = new User("active@example.com", "password");
    user.setActive(true);

    String message = greetingService.getAppropriateMessage(user);

    assertEquals("Your account is active and ready for use!.", message);
  }

  @Test
  void testGreetUserNamedBob() {
    User user = new User("bob@example.com", "password");
    user.setActive(true);

    String message = greetingService.getAppropriateMessage(user);

    assertEquals("Thanks for being my only user!", message);
  }
}