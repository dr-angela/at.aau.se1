public class FizzBuzz {

    // Main method: Entry point of the program
    public static void main(String[] args) {
        // Array of strings to be checked and modified
        String[] words = {"Hello", "SE1", "FizzBuzz", "Test", ":)"};

        // Process each word in the array with fizzBuzz rules
        fizzBuzz(words);

        // Print each word after applying fizzBuzz rules
        for (String word : words) {
            System.out.println(word);
        }
    }

    // fizzBuzz method: Modifies each word in the array based on certain conditions
    public static void fizzBuzz(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String newWord = word;

            // If the word satisfies both "Fizzy" and "Buzzy" conditions, replace with "FIZZ_BUZZ"
            if (isFizzBuzzy(word)) {
                newWord = "FIZZ_BUZZ";
            }
            // If the word only satisfies the "Fizzy" condition, replace with "FIZZ"
            else if (isFizzy(word)) {
                newWord = "FIZZ";
            }
            // If the word only satisfies the "Buzzy" condition, replace with "BUZZ"
            else if (isBuzzy(word)) {
                newWord = "BUZZ";
            }

            // Update the word in the array with the new value
            words[i] = newWord;
        }
    }

    // isFizzBuzzy method: Returns true if the word satisfies both "Fizzy" and "Buzzy" conditions
    public static boolean isFizzBuzzy(String s) {
        return isFizzy(s) && isBuzzy(s);
    }

    // isFizzy method: Placeholder method that always returns false
    // (This could be modified to implement a specific "Fizzy" condition)
    public static boolean isFizzy(String s) {
        System.out.println("isFizzy is not implemented yet.");
        return false;
    }

    // isBuzzy method: Returns true if the length of the word is a multiple of 4
    public static boolean isBuzzy(String s) {
        return s.length() % 4 == 0;
    }
}