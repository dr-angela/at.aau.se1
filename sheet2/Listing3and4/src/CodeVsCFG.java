public class CodeVsCFG {

    // Method from Listing 3
    public static int run_1() {
        int res = 0; // Initialize result to 0

        int i = 0; // Initialize i to 0
        while (i < 0) { // Loop that never executes, as i is not less than 0
            res = res + 1;
        }

        while (i >= 0) { // Infinite loop as i is always 0 (i never changes)
            res = res + 1;
        }

        if (i == 0) { // This condition is true, but due to the previous infinite loop, it's unreachable
            res = res + 1;
        }

        return res; // Return the result
    }

    // Method from Listing 4
    public static int run_2(int[] a) {
        if (a.length == 0) { // Check if the array is empty
            return 0; // Return 0 if the array is empty
        }

        int sum = 0; // Initialize sum to 0
        for (int i = 0, j = a.length; i < a.length; i++, j--) { // Loop over array with two indices
            sum += a[i + 1] + a[j - 1]; // Add elements from both ends of the array
        }

        return sum; // Return the computed sum
    }

    public static void main(String[] args) {
        // Run method from Listing 3
        // Warning: run_1 will result in an infinite loop due to the while (i >= 0) loop.
        // Uncomment the following line if you want to observe the behavior, but it will hang indefinitely.
        // System.out.println("Result of run_1(): " + run_1());

        // Run method from Listing 4 with sample arrays
        int[] array1 = {}; // Empty array
        int[] array2 = {1, 2, 3, 4}; // Sample array

        System.out.println("Result of run_2() with empty array: " + run_2(array1));
        System.out.println("Result of run_2() with array {1, 2, 3, 4}: " + run_2(array2));
    }
}