package at.aau.serg.exercises.usertesting.NACHNAME.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserService implements UserService {

  private final PasswordEncoder passwordEncoder;

  protected final List<User> users = new ArrayList<>();

  public InMemoryUserService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User register(String username, String password) {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username cannot be null or blank");
    }
    if (password == null || password.length() < 6) {
      throw new IllegalArgumentException("Password must be at least 6 characters");
    }
    if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
      throw new IllegalStateException("User already exists");
    }
    User user = new User(username, passwordEncoder.encode(password));
    users.add(user);

    return user;
  }

  @Override
  public boolean login(String username, String password) {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username cannot be null or blank");
    }

    return users.stream()
        .anyMatch(u -> u.getUsername().equals(username)
            && u.isActivated()
            && u.isActive()
            && password.equals(u.getPassword())
        );
  }

  @Override
  public void resetPassword(String username, String newPassword) {
    // TODO implement
  }

  @Override
  public void activateUser(String username) {
    getUser(username).setActivated(true);
  }

  private User getUser(String username) {
    var user = users.stream()
        .filter(u -> u.getUsername().equals(username))
        .filter(User::isActive)
        .findFirst();

    if (user.isEmpty()) {
      throw new IllegalStateException("User does not exist");
    }

    return user.get();
  }
}
