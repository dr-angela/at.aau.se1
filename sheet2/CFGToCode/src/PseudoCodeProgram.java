public class PseudoCodeProgram {
    public static void main(String[] args) {
        testValues();
    }

    // S6: Initialization
    public static void testValues() {
        // Test values for c
        int[] cValues = {0, 1, 2, 3, 4, 5, 6, -1, -5};
        // Test values for num2
        int[] num2Values = {0, 1, 2, 3, 4, 5, -1, -2, 10, -10};

        for (int i = 0; i < cValues.length; i++) {
            int c = cValues[i];
            int num2 = num2Values[i % num2Values.length];
            System.out.println("\nTesting with c = " + c + ", num2 = " + num2);
            executeLogic(c, num2);
        }
    }

    public static void executeLogic(int c, int num2) {
        // S6 to S10 (without condition)

        // S10: Condition to exit or proceed
        if (c < 0) {
            // From S10, exit if condition is false
            System.out.println("S10: c < 0, exiting loop.");
            return;
        }

        // Start of the loop that represents the control flow
        while (c >= 0) {
            // S11-19: Executing statements when S10 condition is true
            System.out.println("S11-19: Executing statements.");

            // S21: Proceed without condition
            System.out.println("S21: Reached.");

            // S22: Exit if c == 0
            if (c == 0) {
                System.out.println("S22: c == 0, exiting.");
                break; // Exit the loop
            } else {
                // S26: Check if c < 1 || c > 5
                if (c < 1 || c > 5) {
                    // S27: Invalid c, return to S10
                    System.out.println("S27: Invalid c (" + c + "), returning to S10.");
                    // Adjust c for demonstration purposes
                    c = (c > 5) ? c - 1 : c + 1;
                    continue; // Return to the start of the loop
                } else {
                    // S31-34: Valid c, proceed
                    System.out.println("S31-34: Valid c (" + c + "), proceeding.");

                    // S36: Switch cases based on c
                    System.out.println("S36: Switching based on c.");

                    switch (c) {
                        case 1:
                            // S38: c == 1
                            System.out.println("S38: c == 1.");
                            // S57: Return to S10
                            break;
                        case 2:
                            // S41: c == 2
                            System.out.println("S41: c == 2.");
                            // S57: Return to S10
                            break;
                        case 3:
                            // S44: c == 3
                            System.out.println("S44: c == 3.");
                            // S57: Return to S10
                            break;
                        case 4:
                            // S47: c == 4
                            System.out.println("S47: c == 4.");
                            if (num2 == 0) {
                                // S48: num2 == 0, return to S10
                                System.out.println("S48: num2 == 0, cannot divide by zero, returning to S10.");
                            } else {
                                // S51: num2 != 0
                                System.out.println("S51: num2 != 0, proceeding with division.");
                            }
                            // S57: Return to S10
                            break;
                        case 5:
                            // Assuming c == 5 represents another operation
                            System.out.println("SXX: c == 5.");
                            // S57: Return to S10
                            break;
                        default:
                            // S54: Default case, return to S10
                            System.out.println("S54: Default case, invalid c, returning to S10.");
                            break;
                    }
                }
            }

            // S57: Return to S10
            System.out.println("S57: Completed iteration, returning to S10.");
            // For demonstration, break to avoid infinite loop
            break;
        }
        System.out.println("Program terminated for c = " + c + ".");
    }
}