import java.util.Scanner;

public class IntroductionExercise {

    public static int binary2decimal(int[] binary) {
        // Überprüfen, ob der Input null oder leer ist
        if (binary == null || binary.length == 0) {
            throw new IllegalArgumentException("Die Eingabe darf nicht null oder leer sein.");
        }

        int decimal = 0;
        int length = binary.length;

        // Umwandlung von Binär zu Dezimal
        for (int i = 0; i < length; i++) {
            // Überprüfen, ob der Binärwert 0 oder 1 ist
            if (binary[i] != 0 && binary[i] != 1) {
                throw new IllegalArgumentException("Binärzahlen dürfen nur 0 oder 1 enthalten.");
            }
            decimal += binary[i] * Math.pow(2, length - 1 - i);
        }

        return decimal;
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // Eingabeaufforderung
            System.out.println("Bitte geben Sie die Binärzahl als eine Sequenz von 0 und 1 ein (z.B. 1101): ");
            String input = scanner.nextLine();

            // Überprüfen, ob die Eingabe leer ist
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("Die Eingabe darf nicht leer sein.");
            }

            // Konvertiere den String in ein Integer-Array
            int[] binaryArray = new int[input.length()];
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                // Überprüfen, ob die Eingabe nur 0 und 1 enthält
                if (c != '0' && c != '1') {
                    throw new IllegalArgumentException("Ungültige Eingabe. Nur 0 und 1 sind erlaubt.");
                }

                binaryArray[i] = Character.getNumericValue(c);
            }

            // Berechnung der Dezimalzahl
            int decimal = binary2decimal(binaryArray);
            System.out.println("Die Dezimalzahl ist: " + decimal);

        } catch (IllegalArgumentException e) {
            System.out.println("Fehler: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }
    }
}