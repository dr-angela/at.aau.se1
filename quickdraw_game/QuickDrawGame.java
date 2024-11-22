// QuickDrawGame.java
import java.util.Scanner;

public class QuickDrawGame {
    public static void main(String[] args) {
        System.out.println("Welcome to the Quickdraw Game!");
        System.out.println("Press ENTER as fast as you can when you see 'DRAW!'");

        try {
            Thread.sleep((int)(Math.random() * 5000) + 1000); // Random delay between 1-6 seconds
        } catch (InterruptedException e) {
            System.out.println("Error occurred!");
        }

        System.out.println("DRAW!");

        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        long endTime = System.currentTimeMillis();

        long reactionTime = endTime - startTime;

        System.out.println("Your reaction time: " + reactionTime + "ms");
        if (reactionTime < 300) {
            System.out.println("Quickdraw! You're lightning fast!");
        } else if (reactionTime < 600) {
            System.out.println("Not bad! You can be faster!");
        } else {
            System.out.println("Too slow! Practice makes perfect.");
        }

        // New feature: Game Over message
        System.out.println("Game Over. Thanks for playing!");
    }
}