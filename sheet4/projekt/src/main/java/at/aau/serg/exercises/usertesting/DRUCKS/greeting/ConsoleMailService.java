package at.aau.serg.exercises.usertesting.DRUCKS.greeting;

import at.aau.serg.exercises.usertesting.DRUCKS.user.User;

public class ConsoleMailService implements MailService {
  @Override
  public void sendMailToUser(User user, String subject, String body) {
    System.out.printf("==== Sending Mail to %s ====%n", user.getUsername());
  }

  @Override
  public void sendMailToUserAsync(User user, String subject, String body) {
    new Thread(() -> sendMailToUser(user, subject, body)).start();
  }

  @Override
  public boolean isValidEmailAddress(String email) {
    return false;
  }
}
