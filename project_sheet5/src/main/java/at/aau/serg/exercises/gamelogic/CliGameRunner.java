package at.aau.serg.exercises.gamelogic;

import at.aau.serg.exercises.backend.entity.GameInstance;

import java.util.Random;

public class CliGameRunner {

    public GameInstance game;
    Random random = new Random();

    public CliGameRunner(GameInstance game) {
        // check if game is playable
        if (!game.canStartGame()) {
            throw new IllegalStateException("Game is not ready to play");
        }

        this.game = game;
    }

    public void startGame() {
        if (!game.isRunning()) {
            game.setRunning(true);
        } else {
            throw new IllegalStateException("Game is already running");
        }
    }

    public void playFirstRound() {
        if (game.isRunning() && game.getTotalRounds() == game.getRemainingRounds()) {
            if (game.isPlayTutorial()) {
                System.out.println("Playing tutorial");
                game.decreaseRemainingRounds(1);
            } else {
                playRound();
            }

        } else {
            throw new IllegalStateException("Game is not running");
        }
    }

    public void playNextRound() {
        if (game.isRunning() && game.getRemainingRounds() < game.getTotalRounds()) {
            if (game.getRemainingRounds() > 0) {
                playRound();
            } else {
                throw new IllegalStateException("No rounds left");
            }
        } else {
            throw new IllegalStateException("Game is not running or still first round");
        }
    }

    private void playRound() {
        game.setLeadingPlayer(game.getPlayers().get(random.nextInt(game.getPlayers().size())));
        System.out.println("Playing round " + (game.getTotalRounds() - game.getRemainingRounds()) + ", won by " + game.getLeadingPlayer().getUsername());
        game.decreaseRemainingRounds(1);
    }

    public void concludeGame() {
        if (game.isRunning() && game.getRemainingRounds() == 0) {
            game.setRunning(false);
            game.setWinner(game.getLeadingPlayer());
            System.out.println("Game Over");
        } else {
            throw new IllegalStateException("Still rounds left");
        }
    }

    public void showWinner() {
        if (game.getWinner() != null) {
            System.out.println("WINNER: " + game.getWinner().getUsername());
        } else {
            throw new IllegalStateException("No winner available");
        }
    }

}
