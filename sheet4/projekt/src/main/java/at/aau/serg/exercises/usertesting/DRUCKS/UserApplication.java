package at.aau.serg.exercises.usertesting.NACHNAME;

import at.aau.serg.exercises.usertesting.NACHNAME.greeting.ConsoleMailService;
import at.aau.serg.exercises.usertesting.NACHNAME.greeting.GreetingService;
import at.aau.serg.exercises.usertesting.NACHNAME.greeting.MailService;
import at.aau.serg.exercises.usertesting.NACHNAME.user.InMemoryUserService;
import at.aau.serg.exercises.usertesting.NACHNAME.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserApplication {
  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private static final MailService mailService = new ConsoleMailService();

  private static final UserService userService = new InMemoryUserService(passwordEncoder);
  private static final GreetingService greetingService = new GreetingService(mailService);

  public static void main(String[] args) {
    var alice = userService.register("alice@email.com", "pa55word");
    var bob = userService.register("bob@email.com", "5afe_pazzw");

    userService.activateUser(alice.getUsername());
    userService.activateUser(bob.getUsername());

    var aliceLogin = userService.login(alice.getUsername(), alice.getPassword()); // should be true
    var bobLogin = userService.login(bob.getUsername(), "password123"); // should be false

    userService.resetPassword(bob.getUsername(), "password123");
    bobLogin = userService.login(bob.getUsername(), "password123"); // should be true

    mailService.sendMailToUser(alice, "Hello Alice", "We need to talk about your grade!");

    System.out.println("Startup complete");
  }
}
