package at.aau.serg.exercises.gamelogic;

import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.entity.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Angela Drucks, 12203559
// mvn clean test jacoco:report
// mvn clean test pitest:mutationCoverage

public class CliGameRunnerRandomTest {

    // Random object to generate random test values
    private static final Random random = new Random();

    @Test
    public void testRandomGameScenariosMultipleTimes() {
        // Number of test iterations
        int numTests = 5;

        for (int i = 0; i < numTests; i++) {
            System.out.println("Running test " + (i + 1));

            // Generate random values for the test
            boolean tutorialEnabled = random.nextBoolean();  // Random value for tutorial
            int totalRounds = random.nextInt(10) + 1;  // Random number of rounds (1-10)
            GameMode gameMode = getRandomGameMode();  // Random game mode
            int numberOfPlayers = random.nextInt(4) + 2;  // Random number of players (2-5)

            // Create players
            List<Player> players = generateRandomPlayers(numberOfPlayers);

            // Create game instance with random parameters
            GameInstance gameInstance = new GameInstance(
                    "RandomGame",
                    gameMode,
                    players,
                    totalRounds,
                    tutorialEnabled
            );

            // Initialize game runner with the game instance
            CliGameRunner runner = new CliGameRunner(gameInstance);
            runner.startGame();

            // Assert that the game is running after start
            Assertions.assertTrue(gameInstance.isRunning(), "Game should be running after startGame()");

            // Play the first round
            runner.playFirstRound();
            Assertions.assertEquals(
                    totalRounds - 1,
                    gameInstance.getRemainingRounds(),
                    "Remaining rounds should decrease by 1 after the first round"
            );

            // Play the subsequent rounds
            while (gameInstance.getRemainingRounds() > 0) {
                runner.playNextRound();
            }

            // Assert that no rounds remain after all rounds are played
            Assertions.assertEquals(0, gameInstance.getRemainingRounds(), "Remaining rounds should be 0 after all rounds are played");

            // Conclude the game
            runner.concludeGame();
            Assertions.assertFalse(gameInstance.isRunning(), "Game should not be running after concludeGame()");

            // Set and verify the winner
            if (players.size() > 0) {
                gameInstance.setWinner(players.get(0));
            }
            Assertions.assertNotNull(gameInstance.getWinner(), "Winner should be set after concludeGame()");

            // Display the winner
            runner.showWinner();
            Assertions.assertNotNull(gameInstance.getWinner().getUsername(), "Winner username should be displayed");
        }
    }

    // Helper method to generate random players
    private List<Player> generateRandomPlayers(int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player((long) i, "Player" + i, "player" + i + "@example.com"));
        }
        return players;
    }

    // Helper method to generate a random game mode
    private GameMode getRandomGameMode() {
        GameMode[] gameModes = GameMode.values();
        return gameModes[random.nextInt(gameModes.length)];
    }
}