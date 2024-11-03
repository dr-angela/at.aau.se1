public class CodeVSCFGcorrected {

    public static int run_3(int x, int y) {
        System.out.println("Starting run_3 with x = " + x + ", y = " + y);

        // If x is less than or equal to 0, set y to 0
        if (x <= 0) {
            y = 0;
            System.out.println("x <= 0, setting y to 0. New y = " + y);
        }

        // If y is less than or equal to 0, set x to 0 and invert the sign of y
        if (y <= 0) {
            x = 0;
            y = y * -1;
            System.out.println("y <= 0, setting x to 0 and y to -y. New x = " + x + ", new y = " + y);
        }

        int count = 0; // Initialize count to 0
        System.out.println("Initialized count to 0");

        // Loop while (x + 1) / (y + 1) is greater than or equal to 0
        while ((x + 1) / (y + 1) >= 0) {
            System.out.println("Looping in run_3: x = " + x + ", y = " + y + ", count = " + count);
            x = x / 2; // Halve x
            y = y / 3; // Divide y by 3
            count++; // Increment count
            System.out.println("Updated in loop: x = " + x + ", y = " + y + ", count = " + count);

            // Safety check to break out of potential infinite loop for testing
            if (count > 100) {
                System.out.println("Breaking out of loop in run_3 due to high count value to avoid infinite loop");
                break;
            }
        }

        System.out.println("Exiting run_3 with final count = " + count);
        return count; // Return the final count
    }

    public static int run_4(int i) {
        System.out.println("Starting run_4 with i = " + i);

        // If i is less than -10, set i to 10
        if (i < -10) {
            i = 10;
            System.out.println("i < -10, setting i to 10. New i = " + i);
        }

        // For loop: increment i by 1 initially, then loop while i is non-negative
        for (i += 1; i >= 0; i--) {
            System.out.println("Looping in run_4: i = " + i);
            // Removed i += 2 to allow i-- to decrease i until it is negative
        }

        System.out.println("Exiting run_4 with final i = " + i);
        return i; // Return the final value of i
    }

    public static void main(String[] args) {
        // Test cases for run_3
        System.out.println("run_3(5, 10): " + run_3(5, 10));  // x > 0, y > 0
        System.out.println("run_3(0, 10): " + run_3(0, 10));  // x <= 0, y > 0
        System.out.println("run_3(-5, -10): " + run_3(-5, -10));  // x <= 0, y <= 0
        System.out.println("run_3(10, -5): " + run_3(10, -5));  // x > 0, y <= 0

        // Test cases for run_4
        System.out.println("run_4(-20): " + run_4(-20));  // i < -10, triggers i = 10
        System.out.println("run_4(-5): " + run_4(-5));    // i >= -10, does not change i to 10
        System.out.println("run_4(5): " + run_4(5));      // i >= 0, starts from 6 in the loop
    }
}