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
        int[] binaryNumber = {1, 1, 1, 0};
        int decimal = binary2decimal(binaryNumber);
        System.out.println("Die Dezimalzahl ist: " + decimal);
    }
}