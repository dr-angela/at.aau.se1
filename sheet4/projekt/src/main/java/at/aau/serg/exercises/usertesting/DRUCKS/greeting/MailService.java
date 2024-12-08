package at.aau.serg.exercises.usertesting.NACHNAME.greeting;

import at.aau.serg.exercises.usertesting.NACHNAME.user.User;

public interface MailService {

  void sendMailToUser(User user, String subject, String body);

  void sendMailToUserAsync(User user, String subject, String body);

  boolean isValidEmailAddress(String email);
}
