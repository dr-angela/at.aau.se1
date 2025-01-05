package at.aau.serg.exercises.backend.dao;

import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.dao.impl.ListGameInstanceDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

// A. Drucks, 12203559
// In GameInstanceDaoTest, all functionality related to ListGameInstanceDao and the ListDao methods are tested.

public class GameInstanceDaoTest {

    private ListGameInstanceDao gameInstanceDao;

    // Set up a new game instance DAO before each test
    @BeforeEach
    public void setUp() {
        gameInstanceDao = new ListGameInstanceDao();  // Create an instance of ListGameInstanceDao
    }

    // Test inserting a new GameInstance
    @Test
    public void testInsert() {
        // Create a new GameInstance with all required parameters
        GameInstance gameInstance = new GameInstance("Test Game", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);  // Insert a GameInstance object

        assertNotNull(gameInstance.getId());  // Check if ID is set
        assertEquals("Test Game", gameInstance.getName());  // Check if the name is correct
    }

    // Test finding a GameInstance by its ID
    @Test
    public void testFindOne() {
        GameInstance gameInstance = new GameInstance("Test Game", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);  // Insert a GameInstance object

        GameInstance foundGameInstance = gameInstanceDao.findOne(gameInstance.getId());
        assertNotNull(foundGameInstance);  // Check if the object is found
        assertEquals(gameInstance.getId(), foundGameInstance.getId());  // Check if the ID matches
        assertEquals(gameInstance.getName(), foundGameInstance.getName());  // Check if the name matches
    }

    // Test finding all GameInstances
    @Test
    public void testFindAll() {
        GameInstance gameInstance1 = new GameInstance("Test Game 1", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstanceDao.insert(gameInstance1);  // Insert the first GameInstance

        GameInstance gameInstance2 = new GameInstance("Test Game 2", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstanceDao.insert(gameInstance2);  // Insert the second GameInstance

        assertEquals(2, gameInstanceDao.findAll().size());  // Check if both objects are in the list
    }

    // Test deleting a GameInstance
    @Test
    public void testDelete() {
        GameInstance gameInstance = new GameInstance("Test Game to Delete", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);  // Insert a GameInstance object

        boolean deleted = gameInstanceDao.delete(gameInstance.getId());  // Delete the GameInstance
        assertTrue(deleted);  // Check if the deletion was successful

        GameInstance deletedGameInstance = gameInstanceDao.findOne(gameInstance.getId());
        assertNull(deletedGameInstance);  // Check if the object is no longer found
    }

    // Test updating a GameInstance
    @Test
    public void testUpdate() {
        // Erstellen eines GameInstance-Objekts mit einem alten Namen
        GameInstance gameInstance = new GameInstance("Old Game Name", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);  // Einfügen des GameInstance-Objekts

        // Erstellen eines neuen GameInstance-Objekts mit dem neuen Namen
        gameInstance = new GameInstance(gameInstance.getId(), "Updated Game Name", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.update(gameInstance);  // Update des GameInstance-Objekts

        // Überprüfen, ob der Name aktualisiert wurde
        assertEquals("Updated Game Name", gameInstance.getName());  // Check if the name was updated
    }
}