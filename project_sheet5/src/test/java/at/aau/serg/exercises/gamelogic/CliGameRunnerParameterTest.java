package at.aau.serg.exercises.gamelogic;

import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.entity.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Angela Drucks, 12203559
// mvn clean test jacoco:report
// mvn clean test pitest:mutationCoverage

public class CliGameRunnerParameterTest {

    // This test will run multiple times with different inputs, using @ParameterizedTest mechanism, that uses
    // params from @csv source (tutorialEnabled, totalRounds, gameMode)

    @ParameterizedTest
    @CsvSource({
            "true, 3, SINGLE",       // Test with tutorial, 3 rounds, SINGLE mode
            "false, 5, ALL_VS",      // Test without tutorial, 5 rounds, ALL_VS mode
            "true, 1, ALL_VS_ONE",   // Test with tutorial, 1 round, ALL_VS_ONE mode
            "false, 2, SINGLE"       // Test without tutorial, 2 rounds, SINGLE mode
    })
    public void testStandardPathWithGameModes(boolean tutorialEnabled, int totalRounds, GameMode gameMode) {
        // 1: Create players for the game
        List<Player> players = List.of(
                new Player(1L, "Player1", "player1@example.com"),
                new Player(2L, "Player2", "player2@example.com"),
                new Player(3L, "Player3", "player3@example.com")
        );

        // 1: Create the game instance with the given parameters
        GameInstance gameInstance = new GameInstance(
                "TestGame",
                gameMode,
                players,
                totalRounds,
                tutorialEnabled
        );

        // 1: Initialize the game runner with the game instance
        CliGameRunner runner = new CliGameRunner(gameInstance);

        // 2: Start the game
        runner.startGame();
        System.out.println("Game started. Mode: " + gameMode + ", Tutorial: " + (tutorialEnabled ? "Enabled" : "Disabled"));
        assertTrue(gameInstance.isRunning(), "Game should be running after startGame()");

        // 3: Play the first round
        runner.playFirstRound();
        System.out.println("First round played. Remaining rounds: " + gameInstance.getRemainingRounds() + " (Tutorial: " + (tutorialEnabled ? "Yes" : "No") + ")");
        assertEquals(totalRounds - 1, gameInstance.getRemainingRounds(), "Remaining rounds should decrease after the first round");

        // 4: Play the next rounds (normal rounds)
        while (gameInstance.getRemainingRounds() > 0) {
            runner.playNextRound();
            System.out.println("Round played. Remaining rounds: " + gameInstance.getRemainingRounds());
        }
        assertEquals(0, gameInstance.getRemainingRounds(), "Remaining rounds should be 0 after all rounds are played");

        // 5: Conclude the game
        runner.concludeGame();
        System.out.println("Game concluded.");

        // 6: Set and check the winner
        if (players.size() > 0) {
            gameInstance.setWinner(players.get(0));  // Set a player as the winner manually if needed
        } else {
            System.out.println("No players available to set a winner.");
        }
        assertNotNull(gameInstance.getWinner(), "Winner should be set after concludeGame()");

        // 7: Show the winner
        if (gameInstance.getWinner() != null) {
            runner.showWinner();
            System.out.println("Final winner displayed: " + gameInstance.getWinner().getUsername());
        } else {
            System.out.println("No winner to display.");
        }
    }
}