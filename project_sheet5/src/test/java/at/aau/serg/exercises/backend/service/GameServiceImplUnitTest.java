package at.aau.serg.exercises.backend.service;

import at.aau.serg.exercises.backend.dao.GameInstanceDao;
import at.aau.serg.exercises.backend.dao.PlayerDao;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.gamelogic.GameRunner;
import at.aau.serg.exercises.backend.service.exception.GameServiceException;
import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

// Angela Drucks, 12203559
// mvn clean test jacoco:report
// mvn clean test pitest:mutationCoverage
// In GameServiceImplUnitTest, all functionality related to game creation, deletion, game state management,
// and player associations with games are tested.


public class GameServiceImplUnitTest {

    @Mock
    private GameInstanceDao gameInstanceDao;

    @Mock
    private PlayerDao playerDao;

    @Mock
    private GameRunner gameRunner;

    @InjectMocks
    private GameServiceImpl gameService;

    private Player player1;
    private Player player2;

    private GameInstance gameInstance;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create players
        player1 = new Player(1L, "Player 1", "player1@example.com");
        player2 = new Player(2L, "Player 2", "player2@example.com");

        // Create game instance mock
        gameInstance = mock(GameInstance.class);  // Mock GameInstance

        // Mock PlayerDao to return players
        when(playerDao.findOne(player1.getId())).thenReturn(player1);
        when(playerDao.findOne(player2.getId())).thenReturn(player2);

        // Mock GameInstanceDao insert method to return a game with the expected name "New Game"
        when(gameInstanceDao.insert(any(GameInstance.class))).thenAnswer(invocation -> {
            GameInstance gameToInsert = invocation.getArgument(0);
            // Return a new GameInstance with the same attributes but set name to "New Game"
            return new GameInstance("New Game", gameToInsert.getGameMode(), gameToInsert.getPlayers(),
                    gameToInsert.getTotalRounds(), gameToInsert.isPlayTutorial());
        });

        // Mock GameInstanceDao findOne method
        when(gameInstanceDao.findOne(anyLong())).thenReturn(gameInstance);
    }

    @Test
    public void testCreateGameWithValidPlayers() throws GameServiceException {
        // Given a list of valid players
        List<Player> players = Arrays.asList(player1);

        // When the game is created
        GameInstance createdGame = gameService.createGame("New Game", GameMode.SINGLE, players, 5, false);

        // Then the game should be created successfully
        assertNotNull(createdGame);
        assertEquals("New Game", createdGame.getName());
        assertTrue(createdGame.getPlayers().contains(player1));
    }

    @Test
    public void testCreateGameWithNonExistentPlayer() {
        // Given a list of players with a non-existent player
        Player nonExistentPlayer = new Player(999L, "Non Existent", "nonexistent@example.com");
        List<Player> players = Arrays.asList(player1, nonExistentPlayer);

        // When creating the game, it should throw an exception
        assertThrows(GameServiceException.class, () -> {
            gameService.createGame("Game with Non Existent Player", GameMode.SINGLE, players, 5, false);
        });
    }

    @Test
    public void testStartGameWhenReady() throws GameServiceException {
        // Mock that the game is ready to start
        when(gameInstance.canStartGame()).thenReturn(true);

        // When we start the game
        boolean started = gameService.startGame(gameInstance);

        // Then the game should start successfully
        assertTrue(started);
        verify(gameRunner).start(gameInstance);  // Ensure GameRunner's start method was called
    }

    @Test
    public void testStartGameWhenNotReady() throws GameServiceException {
        // Mock that the game is not ready to start
        when(gameInstance.canStartGame()).thenReturn(false);

        // When we try to start the game
        boolean started = gameService.startGame(gameInstance);

        // Then the game should not start
        assertFalse(started);
        verify(gameRunner, never()).start(gameInstance);  // Ensure GameRunner's start method was never called
    }

    @Test
    public void testDeleteGameInstance() throws GameServiceException {
        // When deleting the game instance
        gameService.deleteGameInstance(gameInstance);

        // Then the game instance should be deleted
        verify(gameInstanceDao).delete(gameInstance.getId());
    }

    @Test
    public void testDeleteNonExistentGame() {
        // Mock that the game does not exist
        when(gameInstanceDao.findOne(gameInstance.getId())).thenReturn(null);

        // When trying to delete a non-existent game
        assertThrows(GameServiceException.class, () -> {
            gameService.deleteGameInstance(gameInstance);
        });
    }

    @Test
    public void testFindAllGamesByPlayer() throws GameServiceException {
        // Given a list of games
        GameInstance game1 = new GameInstance("Game 1", GameMode.SINGLE, Arrays.asList(player1), 5, false);
        GameInstance game2 = new GameInstance("Game 2", GameMode.ALL_VS, Arrays.asList(player1, player2), 5, false);
        when(gameInstanceDao.findAll()).thenReturn(Arrays.asList(game1, game2));

        // When finding games for player1
        List<GameInstance> games = gameService.findAllGamesByPlayer(player1);

        // Then it should return the games that include player1
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }

    @Test
    public void testFindGamesByPlayerWithNoGames() throws GameServiceException {
        // Mock that no games are found
        when(gameInstanceDao.findAll()).thenReturn(Arrays.asList());

        // When finding games for player1
        List<GameInstance> games = gameService.findAllGamesByPlayer(player1);

        // Then it should return an empty list
        assertTrue(games.isEmpty());
    }
}