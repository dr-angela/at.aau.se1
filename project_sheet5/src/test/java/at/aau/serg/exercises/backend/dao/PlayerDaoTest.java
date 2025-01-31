package at.aau.serg.exercises.backend.dao;

import at.aau.serg.exercises.backend.dao.impl.ListPlayerDao;
import at.aau.serg.exercises.backend.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A. Drucks, 12203559
// In PlayerDaoTest, all functionality related to ListPlayerDao and the ListDao methods are tested.

public class PlayerDaoTest {

    private ListPlayerDao playerDao;

    // Set up a new Player DAO before each test
    @BeforeEach
    public void setUp() {
        playerDao = new ListPlayerDao();  // Create an instance of ListPlayerDao
    }

    // Test inserting a new Player
    @Test
    public void testInsert() {
        // Create a new Player with username and email
        Player player = new Player(null, "Player 1", "player1@example.com");  // ID is null initially
        player = playerDao.insert(player);  // Insert a Player object

        assertNotNull(player.getId());  // Check if ID is set after insertion
        assertEquals("Player 1", player.getUsername());  // Check if the username is correct
    }

    // Test finding a Player by its ID
    @Test
    public void testFindOne() {
        Player player = new Player(null, "Player 1", "player1@example.com");
        player = playerDao.insert(player);  // Insert a Player object

        Player foundPlayer = playerDao.findOne(player.getId());
        assertNotNull(foundPlayer);  // Check if the object is found
        assertEquals(player.getId(), foundPlayer.getId());  // Check if the ID matches
        assertEquals(player.getUsername(), foundPlayer.getUsername());  // Check if the username matches
    }

    // Test finding all Players
    @Test
    public void testFindAll() {
        Player player1 = new Player(null, "Player 1", "player1@example.com");
        playerDao.insert(player1);  // Insert the first Player

        Player player2 = new Player(null, "Player 2", "player2@example.com");
        playerDao.insert(player2);  // Insert the second Player

        assertEquals(2, playerDao.findAll().size());  // Check if both objects are in the list
    }

    // Test deleting a Player
    @Test
    public void testDelete() {
        Player player = new Player(null, "Player to Delete", "delete@example.com");
        player = playerDao.insert(player);  // Insert a Player object

        boolean deleted = playerDao.delete(player.getId());  // Delete the Player
        assertTrue(deleted);  // Check if the deletion was successful

        Player deletedPlayer = playerDao.findOne(player.getId());
        assertNull(deletedPlayer);  // Check if the object is no longer found
    }

    // Test updating a Player
    @Test
    public void testUpdate() {
        // Create a Player object with an old username
        Player player = new Player(null, "Old Player Name", "oldplayer@example.com");
        player = playerDao.insert(player);  // Insert the Player object

        // Create a new Player object with the updated username
        player = new Player(player.getId(), "Updated Player Name", "updatedplayer@example.com");
        player = playerDao.update(player);  // Update the Player object

        // Check if the username was updated correctly
        assertEquals("Updated Player Name", player.getUsername());  // Verify if the username was updated
    }

    // Test attempting to delete a Player twice
    @Test
    public void testDoubleDelete() {
        // Create a new Player
        Player player = new Player(null, "Player to Double Delete", "doubledelete@example.com");
        player = playerDao.insert(player);  // Insert a Player object

        // Delete the Player once
        boolean firstDelete = playerDao.delete(player.getId());
        assertTrue(firstDelete);  // Ensure the player was deleted successfully

        // Attempt to delete the Player again
        boolean secondDelete = playerDao.delete(player.getId());
        assertFalse(secondDelete);  // Ensure the second delete attempt fails (player should no longer exist)
    }

    // Test inserting a Player with null or empty username
    @Test
    public void testInsertPlayerWithNullUsername() {
        Player player = new Player(null, null, "nullusername@example.com");
        player = playerDao.insert(player);  // Insert a Player object with null username

        assertNotNull(player.getId());  // Check if ID is set after insertion
        assertNull(player.getUsername());  // Check if the username is null as expected
    }

    @Test
    public void testInsertPlayerWithEmptyUsername() {
        Player player = new Player(null, "", "emptyusername@example.com");
        player = playerDao.insert(player);  // Insert a Player object with empty username

        assertNotNull(player.getId());  // Check if ID is set after insertion
        assertEquals("", player.getUsername());  // Check if the username is empty
    }

    // Test finding a Player with an invalid ID
    @Test
    public void testFindPlayerWithInvalidId() {
        Player foundPlayer = playerDao.findOne(-1L);  // Attempt to find a Player with an invalid ID
        assertNull(foundPlayer);  // Ensure no Player is found with an invalid ID
    }



}