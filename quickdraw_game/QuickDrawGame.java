// QuickDrawGame.java
import java.util.Scanner;

public class QuickDrawGame {
    public static void main(String[] args) {
        System.out.println("Welcome to the Quickdraw Game!");
        System.out.println("Press ENTER as fast as you can when you see 'DRAW!'");
        System.out.println("Get ready...");

        // Countdown Timer: 3... 2... 1...
        try {
            for (int i = 3; i > 0; i--) {
                System.out.println(i + "...");
                Thread.sleep(1000); // 1 second delay
            }
        } catch (InterruptedException e) {
            System.out.println("Countdown interrupted!");
        }

        // Random delay before "DRAW!"
        try {
            Thread.sleep((int)(Math.random() * 5000) + 1000); // Random delay between 1-6 seconds
        } catch (InterruptedException e) {
            System.out.println("Error occurred!");
        }

        System.out.println("DRAW!");

        // Measure reaction time
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        long endTime = System.currentTimeMillis();

        long reactionTime = endTime - startTime;

        // Display reaction time and feedback
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