public class Abbildung1 {

    public static void main(String[] args) {
        testValues();
    }

    public static void testValues() {
        int[] cValues = {0, 1, 2, 3, 4, 5, 6, 10, -1, -5}; // Testwerte für c
        int[] num2Values = {0, 1, 2, 3, 4, 5, -1, -2, 10, -10}; // Testwerte für num2

        for (int i = 0; i < cValues.length; i++) {
            int c = cValues[i];
            int num2 = num2Values[i];
            System.out.println("\nTesting with c = " + c + ", num2 = " + num2);
            executeLogic(c, num2);
        }
    }

    public static void executeLogic(int c, int num2) {
        // Knoten 6 bis 10
        if (c > 0) {  // Beispielbedingung für den if-Block
            // Knoten 11-19: Schleife oder Anweisungsblock
            for (int i = 0; i < 5; i++) {  // Beispielgrenze für die Schleife
                System.out.println("Looping in Knoten 11-19, i = " + i);
            }

            // Knoten 26 bis 27
            if (c > 5) {
                System.out.println("Knoten 31-34: c > 5");
                // Code für diese Bedingung
            } else {
                // Knoten 36 bis 57: switch-Statement
                switch (c) {
                    case 1:
                        System.out.println("Knoten 38: Fall c == 1");
                        break;
                    case 2:
                        System.out.println("Knoten 41: Fall c == 2");
                        break;
                    case 3:
                        System.out.println("Knoten 44: Fall c == 3");
                        break;
                    case 4:
                        System.out.println("Knoten 47: Fall c == 4");
                        // Knoten 48 bis 54
                        if (num2 == 0) {
                            System.out.println("Knoten 54: num2 == 0");
                        } else {
                            System.out.println("Knoten 51: num2 != 0");
                        }
                        break;
                    default:
                        System.out.println("Knoten 54: default-Fall");
                        break;
                }
            }
        } else {
            System.out.println("Knoten false: c <= 0");
        }
    }
}