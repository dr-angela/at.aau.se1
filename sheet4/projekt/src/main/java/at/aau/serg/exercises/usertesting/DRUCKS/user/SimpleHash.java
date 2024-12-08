package at.aau.serg.exercises.usertesting.NACHNAME.user;

public class SimpleHash {

  /**
   * @param input the string to hash
   * @return a simple hash for the given string.
   * @throws IllegalArgumentException if the input is null or empty
   */
  public static int simpleHash(String input) {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException("Input cannot be null or empty");
    }

    int hash = 0;
    for (int i = 0; i < input.length(); i++) {
      hash += input.charAt(i) * (i + 1);
    }

    if (hash % 2 == 1) {
      hash++;
    }

    return hash;
  }

}
