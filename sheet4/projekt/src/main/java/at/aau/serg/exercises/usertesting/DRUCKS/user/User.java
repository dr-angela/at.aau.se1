package at.aau.serg.exercises.usertesting.DRUCKS.user;

public class User {
  private static int nextId = 1;

  private final int id;

  private final String username;
  private String password;
  private boolean isActivated;
  private boolean isActive = true;

  public User(String username, String password) {
    this.id = nextId++;
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean isActivated() {
    return isActivated;
  }

  public void setActivated(boolean activated) {
    isActivated = activated;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
