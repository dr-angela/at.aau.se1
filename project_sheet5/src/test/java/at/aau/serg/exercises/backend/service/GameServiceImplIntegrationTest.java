package at.aau.serg.exercises.backend.service;

import at.aau.serg.exercises.backend.dao.GameInstanceDao;
import at.aau.serg.exercises.backend.dao.PlayerDao;
import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.entity.Player;
import at.aau.serg.exercises.backend.gamelogic.GameRunner;
import at.aau.serg.exercises.backend.service.exception.GameServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Angela Drucks, 12203559
// mvn clean test jacoco:report
// mvn clean test pitest:mutationCoverage
// In GameServiceImplIntegrationTest, all functionality related to game creation, deletion, game state management,
// and player associations with games are tested. This includes various game modes (SINGLE, ALL_VS, ALL_VS_ONE) and
// the handling of non-existent players and games.
// mock data only exists during run time of tests

public class GameServiceImplIntegrationTest {

    // Mocks for GameInstanceDao and PlayerDao
    @Mock
    private GameInstanceDao gameInstanceDao;

    @Mock
    private PlayerDao playerDao;

    // Service class being tested
    @InjectMocks
    private GameServiceImpl gameService;

    // Test data: Players
    private Player player1;
    private Player player2;

    // Mock for GameInstance
    @Mock
    private GameInstance gameInstance;

    @Mock
    private GameRunner gameRunner;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Test data
        player1 = new Player(1L, "Player 1", "player1@example.com");
        player2 = new Player(2L, "Player 2", "player2@example.com");

        // Mock DAO behavior
        when(playerDao.findOne(player1.getId())).thenReturn(player1);
        when(playerDao.findOne(player2.getId())).thenReturn(player2);

        // Mock `gameInstanceDao.insert` to return a GameInstance with a simulated database generated ID
        when(gameInstanceDao.insert(any(GameInstance.class)))
                .thenAnswer(invocation -> {
                    GameInstance game = invocation.getArgument(0);
                    game.setId(1L);
                    return game;
                });

        // Mock `gameInstanceDao.findOne` to return the gameInstance mock
        when(gameInstanceDao.findOne(anyLong())).thenReturn(gameInstance);

    }

    // Tests

    @Test
    public void testCreateGameWithSingleMode() throws GameServiceException {
        // Only one player in the game for Single mode
        List<Player> players = Arrays.asList(player1);

        // Create a game with the name "Test Single Mode Game" in Single mode
        GameInstance createdGame = gameService.createGame("Test Single Mode Game", GameMode.SINGLE, players, 5, false);

        // Assert that the game is created with the expected name
        assertNotNull(createdGame);  // The created game should not be null
        assertEquals("Test Single Mode Game", createdGame.getName());  // The name should match
        assertEquals(1, createdGame.getPlayers().size());  // Only 1 player should be in the game
    }

    @Test
    public void testCreateGameWithAllVsMode() throws GameServiceException {
        // Test for ALL_VS mode with two players
        List<Player> players = Arrays.asList(player1, player2);  // Two players

        GameInstance createdGame = gameService.createGame("All VS Game", GameMode.ALL_VS, players, 5, false);

        // Verify the created game details
        assertNotNull(createdGame);
        assertEquals(GameMode.ALL_VS, createdGame.getGameMode());
        assertEquals(2, createdGame.getPlayers().size());  // Two players in the game
    }

    @Test
    public void testCreateGameWithAllVsOneMode() throws GameServiceException {
        // Test for ALL_VS_ONE mode with two players
        List<Player> players = Arrays.asList(player1, player2);  // Two players

        GameInstance createdGame = gameService.createGame("All VS One Game", GameMode.ALL_VS_ONE, players, 5, false);

        // Verify the created game details
        assertNotNull(createdGame);
        assertEquals(GameMode.ALL_VS_ONE, createdGame.getGameMode());
        assertEquals(2, createdGame.getPlayers().size());  // Two players in the game
    }

    @Test
    public void testCreateGameWithNonExistentPlayer() {
        // Test case when a player does not exist in the database
        Player nonExistentPlayer = new Player(999L, "Non Existent Player", "nonexistent@example.com");
        List<Player> players = Arrays.asList(player1, nonExistentPlayer);

        // Expect a GameServiceException because the player does not exist
        assertThrows(GameServiceException.class, () -> {
            gameService.createGame("Test Game", GameMode.SINGLE, players, 5, false);
        });
    }

    @Test
    public void testDeleteGameInstance() throws GameServiceException {
        // Test case to delete an existing game
        gameService.deleteGameInstance(gameInstance);

        // Verify if the delete method was called once
        verify(gameInstanceDao, times(1)).delete(gameInstance.getId());
    }

    @Test
    public void testDeleteNonExistentGame() {
        // Simulate a non-existent game
        GameInstance nonExistentGame = new GameInstance("Non Existent Game", GameMode.SINGLE, Arrays.asList(player1), 5, false);
        when(gameInstanceDao.findOne(nonExistentGame.getId())).thenReturn(null);

        // Expect a GameServiceException because the game does not exist
        assertThrows(GameServiceException.class, () -> {
            gameService.deleteGameInstance(nonExistentGame);
        });
    }

    @Test
    public void testStartGameWhenReady() throws GameServiceException {
        // Mock behavior for `gameInstance`
        when(gameInstance.canStartGame()).thenReturn(true);

        // Mock behavior for `gameRunner`
        doNothing().when(gameRunner).start(gameInstance);

        // Call the service to start the game
        boolean gameStarted = gameService.startGame(gameInstance);

        // Verify the game was started
        assertTrue(gameStarted); // The game should be started successfully
        verify(gameInstance, times(1)).canStartGame(); // Verify `canStartGame` was called
        verify(gameRunner, times(1)).start(gameInstance); // Verify `start` was called on the runner
    }


    @Test
    public void testStartGameWhenNotReady() throws GameServiceException {
        // Test case when the game is not ready to start
        when(gameInstance.canStartGame()).thenReturn(false); // Mock behavior for `canStartGame`

        // Call the service to start the game
        boolean gameStarted = gameService.startGame(gameInstance);

        // Verify the game was not started
        assertFalse(gameStarted);
        verify(gameInstance, times(1)).canStartGame(); // Verify `canStartGame` was called once
    }

    @Test
    public void testFindAllGamesByPlayer() throws GameServiceException {
        // Mocking game instances
        GameInstance game1 = new GameInstance("Game 1", GameMode.SINGLE, Arrays.asList(player1), 5, false);
        GameInstance game2 = new GameInstance("Game 2", GameMode.ALL_VS, Arrays.asList(player1, player2), 5, false);

        // Mocking gameInstanceDao.findAll() to return a list of games
        when(gameInstanceDao.findAll()).thenReturn(Arrays.asList(game1, game2));

        // Call the service method
        List<GameInstance> games = gameService.findAllGamesByPlayer(player1);

        // Verify the games list is not null or empty
        assertNotNull(games); // List should not be null
        assertFalse(games.isEmpty()); // List should not be empty
        assertEquals(2, games.size()); // Player 1 is part of 2 games
        assertTrue(games.contains(game1)); // Game 1 should be in the list
        assertTrue(games.contains(game2)); // Game 2 should be in the list
    }

    @Test
    public void testFindGamesByPlayerWithNoGames() throws GameServiceException {
        // Mocking the scenario where no games are found for the player, by returning an empty list
        when(gameInstanceDao.findAll()).thenReturn(Arrays.asList());  // Mocking the response to be an empty list

        // Calling the service method to find all games for player1
        List<GameInstance> games = gameService.findAllGamesByPlayer(player1);

        // Asserting that the returned list of games is empty
        assertTrue(games.isEmpty());  // Verifying that no games are returned for player1
    }
}