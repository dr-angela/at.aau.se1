// This was written by test preview GPT o1 in order to
// have another view on Abbildung 1 - I gave GPT a list of
// Pseudo Code, as I had a look on Abbildung 1

public class PseudoCodeProgram {
    public static void main(String[] args) {
        // S6: Initialize variables
        int c = 1;     // You can change this value to test different paths
        int num1 = 10; // Sample value
        int num2 = 0;  // Sample value
        int result = 0;

        // S6 to S10 (without condition)
        // S10: Condition to exit or proceed
        boolean conditionInS10 = true; // Set to true to start the program flow

        while (conditionInS10) {
            // From S10, there is an exit if the condition in S10 is false
            // Since conditionInS10 is true, proceed to S11-19

            // S11-19: Perform some operations or statements
            System.out.println("S11-19: Performing initial operations.");

            // Proceed to S21
            // S21: Check if (c == 0)
            System.out.println("S21: Checking if c == 0");
            if (c == 0) {
                // From S21 to S22 and then exit
                // S22: Exit program
                System.out.println("S22: Exiting program because c == 0");
                break;
            } else {
                // From S21 to S26 if !(c == 0)
                // S26: Check if (c < 1 || c > 5)
                System.out.println("S26: Checking if c < 1 || c > 5");
                if (c < 1 || c > 5) {
                    // From S26 to S27 if (c < 1 || c > 5)
                    // S27: Invalid choice, back to S10
                    System.out.println("S27: Invalid value of c (" + c + "), returning to S10");
                    // Modify c to a valid value or exit loop
                    c = 1; // Adjust c to a valid value to proceed
                    // Back to S10
                    continue;
                } else {
                    // From S26 to S31-34 if !(c < 1 || c > 5)
                    // S31-34: Proceed with valid c
                    System.out.println("S31-34: Valid value of c (" + c + "), proceeding");

                    // Proceed to S36
                    // S36: Switch cases based on c
                    System.out.println("S36: Switching based on value of c");
                    switch (c) {
                        case 1:
                            // From S36 to S38 when (c == 1)
                            // S38: Perform operation for c == 1
                            System.out.println("S38: c == 1, performing addition");
                            result = num1 + num2;
                            System.out.println("Result of addition: " + result);
                            // From S38 to S57
                            break;
                        case 2:
                            // From S36 to S41 when (c == 2)
                            // S41: Perform operation for c == 2
                            System.out.println("S41: c == 2, performing subtraction");
                            result = num1 - num2;
                            System.out.println("Result of subtraction: " + result);
                            // From S41 to S57
                            break;
                        case 3:
                            // From S36 to S44 when (c == 3)
                            // S44: Perform operation for c == 3
                            System.out.println("S44: c == 3, performing multiplication");
                            result = num1 * num2;
                            System.out.println("Result of multiplication: " + result);
                            // From S44 to S57
                            break;
                        case 4:
                            // From S36 to S47 when (c == 4)
                            // S47: Check (num2 == 0)
                            System.out.println("S47: c == 4, checking if num2 == 0 for division");
                            if (num2 == 0) {
                                // From S47 to S48 when (num2 == 0)
                                // S48: Cannot divide by zero, back to S10
                                System.out.println("S48: Cannot divide by zero, returning to S10");
                                // Back to S10
                                break;
                            } else {
                                // From S47 to S51 when (num2 != 0)
                                // S51: Perform division
                                System.out.println("S51: Performing division");
                                result = num1 / num2;
                                System.out.println("Result of division: " + result);
                                // From S51 to S57
                            }
                            break;
                        case 5:
                            // Assuming c == 5 represents a modulus operation
                            System.out.println("SXX: c == 5, performing modulus operation");
                            result = num1 % num2;
                            System.out.println("Result of modulus: " + result);
                            // Proceed to S57
                            break;
                        default:
                            // From S36 to S54 when default
                            // S54: Invalid choice, back to S10
                            System.out.println("S54: Invalid choice in switch, returning to S10");
                            // Back to S10
                            break;
                    }
                    // From S57 back to S10
                    System.out.println("S57: Operation completed, returning to S10");
                    // For demonstration, modify c or conditionInS10 to exit loop
                    conditionInS10 = false; // Set to false to exit the loop
                }
            }
        }
        // Program ends
        System.out.println("Program terminated.");
    }
}