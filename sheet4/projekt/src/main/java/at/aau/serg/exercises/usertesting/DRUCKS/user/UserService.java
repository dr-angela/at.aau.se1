package at.aau.serg.exercises.usertesting.DRUCKS.user;

public interface UserService {

  /**
   * @param username - unique username to identify the new user
   * @param password - passphrase to authenticate the user
   * @return a new user to the system if it does not yet exist. The user is active,
   * but not activated.
   * @throws IllegalArgumentException if username is blank or null
   * @throws IllegalArgumentException if username is not a valid email address
   * @throws IllegalArgumentException if password is blank or null
   * @throws IllegalStateException    if a user with this username already exists
   */
  at.aau.serg.exercises.usertesting.DRUCKS.user.User register(String username, String password);

  /**
   * @param username - unique username to identify the user
   * @param password - passphrase to authenticate the user
   * @return true, if a user with the given credentials exists
   * @throws IllegalArgumentException if username is blank or null
   */
  boolean login(String username, String password);

  /**
   * Changes the password of the user to the new one, if it exists
   *
   * @param username    - unique username to identify the user
   * @param newPassword - new passphrase to authenticate the user
   * @throws IllegalArgumentException if username is blank or null
   * @throws IllegalArgumentException if new password is blank or null
   * @throws IllegalStateException    if the user does not exist or if it is not activated
   */
  void resetPassword(String username, String newPassword);

  /**
   * Sets the activated flag of one user to true, if it exists
   *
   * @param username - unique username to identify the user
   * @throws IllegalArgumentException if username is blank or null
   * @throws IllegalStateException    if the user does not exist
   */
  void activateUser(String username);
}
