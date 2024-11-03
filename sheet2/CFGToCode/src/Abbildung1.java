public class Abbildung1 {

    public static void main(String[] args) {
        testValues();
    }

    // S6 als Knoten für Wertinitialisierung
    public static void testValues() {
        // Testwerte für c
        int[] cValues = {0, 1, 2, 3, 4, 5, 6, 10, -1, -5};
        // Testwerte für num2
        int[] num2Values = {0, 1, 2, 3, 4, 5, -1, -2, 10, -10};

        for (int i = 0; i < cValues.length; i++) {
            int c = cValues[i];
            int num2 = num2Values[i];
            System.out.println("\nTesting with c = " + c + ", num2 = " + num2);
            executeLogic(c, num2);
        }
    }

    public static void executeLogic(int c, int num2) {
        // Prüfen, ob die Bedingung von Knoten 10 false ist
        if (c < 0) {
            System.out.println("Knoten 10 false: c < 0, Schleife wird nicht betreten");
            return;
        }

        // S10: Bedingung
        while (c >= 0) {  // Schleife, die wieder zu Knoten 10 zurückführt
            // Knoten 11-19: if S10 true
            for (int i = 0; i < 5; i++) {
                System.out.println("Looping in Knoten 11-19, i = " + i);
            }

            // Knoten 21
            System.out.println("Knoten 21 erreicht");

            // Knoten 22 oder 26
            if (c == 0) {
                System.out.println("Knoten 22: c == 0 erreicht, Abbruch");
                break;  // Ende der Schleife, falls c == 0
            } else {
                // Knoten 26 bis 27
                if (c < 1 || c > 5) {
                    // Bedingung ist true, also zurück zu S10
                    System.out.println("Zurück zu Knoten S10: Bedingung 'c < 1 oder c > 5' ist wahr");
                    c = (c > 5) ? c - 1 : c + 1;  // Wert von c anpassen, um dynamisch zu bleiben
                    continue;  // Springt zurück zur Bedingung von S10
                } else {
                    // Knoten 31-34: wenn die Bedingung NOT true (also !(c < 1 || c > 5))
                    System.out.println("Knoten 31-34: Bedingung 'c < 1 oder c > 5' ist nicht wahr");
                    // Knoten 36 bis 57: switch-Statement
                    switch (c) {
                        case 1:
                            System.out.println("Knoten 38 zu 57: Fall c == 1");
                            break;
                        case 2:
                            System.out.println("Knoten 41 zu 57: Fall c == 2");
                            break;
                        case 3:
                            System.out.println("Knoten 44 zu 57: Fall c == 3");
                            break;
                        case 4:
                            System.out.println("Knoten 47 zu 51: Fall c == 4");
                            // Knoten 48 bis 54
                            if (num2 == 0) {
                                System.out.println("Knoten 47 zu 48: num2 == 0, zurück zu S10");
                            } else {
                                System.out.println("Knoten 47 zu 51: num2 != 0");
                            }
                            break;
                        default:
                            System.out.println("Knoten 54: default, zurück zu S10");
                            break;
                    }
                }
            }
            System.out.println("Knoten 57 erreicht. Kante zurück nach S10. Erster Durchlauf beendet.");
            break;  // Schleifenabbruch nach dem ersten vollständigen Durchlauf, um Endlosschleifen zu vermeiden
        }
    }
}