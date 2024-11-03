/**
 * This code file was created with assistance from GPT-4.
 * GPT-4 was utilized to design and implement structured log entries
 * following each node transition and condition in the control flow.
 * The log entries aim to clearly trace each step of the execution path
 * for easier debugging and analysis.
 *
 * Key modifications made with GPT-4's guidance:
 * - Added log entries for each transition and condition based on the CFG.
 * - Structured log levels (INFO, ACTION, WARNING, TERMINATE, SUCCESS, ERROR).
 * - Included loop control mechanisms to prevent infinite loops.
 */


import java.util.Random;

public class Abbildung2 {

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        // Test arrays for multiple test cases
        int[] cValues = {1, 2, 3, 4, 0, 5, -1}; // Test values for `c`
        int[] healthValues = {10, 5, 0, 100};        // Test values for `health`

        for (int c : cValues) {
            for (int health : healthValues) {
                log("INFO", "Node 7-14", "Starting test case with initial values c = " + c + ", health = " + health);
                log("INFO", "Passed Node 7-14", "Reached Node 17-19.");
                runFlow(c, health);
                System.out.println("----- End of Test Case (c = " + c + ", health = " + health + ") -----\n");
            }
        }
    }

    public static void runFlow(int c, int health) {
        // Initial setup as per Node 7-14
        boolean hasKey = false; // Represents whether the "key" condition is met
        Random random = new Random();
        int loopCount = 0; // Initialize loop counter

        while (true) {
            // Stop the loop if it exceeds a maximum count to prevent infinite loops during testing
            loopCount++;
            if (loopCount > 10) {  // Limit to 10 loops for testing
                log("WARNING", "Loop Limit", "Reached maximum loop count. Terminating to prevent infinite loop.");
                break;
            }

            log("INFO", "Node 17-19", "Starting main loop with c = " + c + ", health = " + health);
            log("INFO", "Node 17-19", "Transitioning to Node 21 without condition.");

            // Node 21: Check if c == 0
            if (c == 0) {
                log("INFO", "Node 21", "Transitioning to Node 22 because c == 0.");
                log("TERMINATE", "Node 22", "Condition met (c == 0). Exiting program.");
                break; // End of flow
            } else {
                log("INFO", "Node 21", "Transitioning to Node 30 because !(c == 0).");

                // Node 30: Validate c range (1 <= c <= 4)
                if (c < 1 || c > 4) {
                    log("WARNING", "Node 30", "Invalid choice (c < 1 || c > 4), transitioning to Node 31.");
                    log("INFO", "Node 31", "Transitioning to Node 72 because !(health <= 0).");
                    log("INFO", "Node 72", "Continuing loop back to Node 17-19.");
                    c = (c > 4) ? c - 1 : c + 1; // Adjust `c` for valid range
                    continue;
                } else {
                    log("INFO", "Node 30", "Transitioning to Node 35 because !(c < 1 || c > 4).");
                    log("INFO", "Node 35", "Entering switch statement with cases for c = 1, 2, 3, 4, or default.");

                    // Switch statement based on the value of c
                    switch (c) {
                        case 1:
                            log("ACTION", "Node 35", "Transitioning to Node 37 because c == 1.");
                            log("ACTION", "Node 37", "Transitioning to Node 39-41.");
                            log("ACTION", "Node 39-41", "Executing operation for c == 1 and transitioning to Node 65.");
                            break;

                        case 2:
                            log("ACTION", "Node 35", "Transitioning to Node 39-41 because c == 2.");
                            log("ACTION", "Node 39-41", "Executing operation for c == 2 and transitioning to Node 65.");
                            break;

                        case 3:
                            log("ACTION", "Node 35", "Transitioning to Node 44 because c == 3.");
                            log("ACTION", "Node 44", "Executing operation for c == 3.");
                            if (random.nextBoolean()) {
                                log("INFO", "Node 44", "Transitioning to Node 45-46 as r.nextBoolean() is true.");
                                log("INFO", "Node 45-46", "Continuing to Node 65.");
                            } else {
                                log("INFO", "Node 44", "Transitioning to Node 47-48 as r.nextBoolean() is false.");
                                log("INFO", "Node 47-48", "Continuing to Node 65.");
                            }
                            break;

                        case 4:
                            log("ACTION", "Node 35", "Transitioning to Node 53 because c == 4.");
                            log("ACTION", "Node 53", "Executing operation for c == 4.");
                            if (!hasKey) {
                                log("INFO", "Node 53", "Transitioning to Node 54-55 as hasKey is false.");
                                log("INFO", "Node 54-55", "Returning to Node 65.");
                            } else {
                                log("INFO", "Node 53", "Transitioning to Node 57 as hasKey is true.");
                                log("INFO", "Node 57", "Returning to Node 65.");
                            }
                            break;

                        default:
                            log("ERROR", "Node 35", "Transitioning to Node 61 as default case.");
                            log("INFO", "Node 61", "Transitioning to Node 72 without condition.");
                            continue; // Return to start
                    }

                    // Node 65: After completing an operation in the switch statement
                    log("INFO", "Node 65", "Operation completed. Transitioning to Node 67.");

                    // Node 67: Check if health is depleted
                    if (health <= 0) {
                        log("TERMINATE", "Node 67", "Transitioning to Node 68 as health <= 0.");
                        log("TERMINATE", "Node 68", "Health is depleted. Transitioning to Node 74.");
                        log("SUCCESS", "Node 74", "Program reached end due to health depletion.");
                        break; // End of flow
                    } else {
                        log("INFO", "Node 67", "Transitioning to Node 72 as health > 0.");
                        log("INFO", "Node 72", "Transitioning to Node 17-19 if true, or to Node 74 if false.");
                        // Uncomment the line below to reduce health and reach termination naturally
                        // health--; // Decrease health each loop to eventually reach 0
                        if (health > 0) {
                            log("INFO", "Node 72", "Continuing loop back to Node 17-19.");
                        } else {
                            log("SUCCESS", "Node 74", "Final state reached. Program ends.");
                            break;
                        }
                    }
                }
            }
        }

        log("END", "Program", "Execution finished for current test case.");
    }

    private static void log(String level, String node, String message) {
        System.out.println("[" + level + "] " + node + " - " + message);
    }
}