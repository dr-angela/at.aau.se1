package at.aau.serg.exercises.usertesting.DRUCKS.greeting;

import at.aau.serg.exercises.usertesting.DRUCKS.user.User;
import java.util.List;

public class GreetingService {
  protected static final String MSG_TEMPLATE_INACTIVE = "Your account is not activated yet.";
  protected static final String MSG_TEMPLATE_ACTIVE = "Your account is active and ready for use!.";
  protected static final String MSG_NAMED_BOB = "Thanks for being my only user!";

  protected static final List<String> GREETINGS = List.of("Hello %s!", "Hi %s!", "こんにちは %s さん", "Hola %s");

  private final MailService mailService;

  public GreetingService(MailService mailService) {
    this.mailService = mailService;
  }

  public void greetUser(User user, boolean includeGreeting, boolean sendAsync) {
    var msg = getAppropriateMessage(user);

    if (includeGreeting) {
      msg = String.format("%s %s", GREETINGS.stream().findAny().orElseThrow(), msg);
    }

    if (mailService.isValidEmailAddress(user.getUsername())) {
      if (sendAsync) {
        mailService.sendMailToUserAsync(user, "Greetings!", msg);
      } else {
        mailService.sendMailToUser(user, "Greetings!", msg);
      }
    } else {
      System.out.println(msg);
    }
  }

  public String getAppropriateMessage(User user) {
    if (!user.isActive()) {
      return MSG_TEMPLATE_INACTIVE;
    }

    if (user.getUsername().contains("bob")) {
      return MSG_NAMED_BOB;
    }

    return MSG_TEMPLATE_ACTIVE;
  }
}
