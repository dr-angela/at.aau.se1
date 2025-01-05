package at.aau.serg.exercises.backend.dao;

import at.aau.serg.exercises.backend.entity.GameInstance;
import at.aau.serg.exercises.backend.entity.GameMode;
import at.aau.serg.exercises.backend.dao.impl.ListGameInstanceDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

// A. Drucks, 12203559
// mvn clean test jacoco:report
// mvn clean test pitest:mutationCoverage
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
        // Create a GameInstance object with an old name
        GameInstance gameInstance = new GameInstance("Old Game Name", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);  // Insert the GameInstance object

        // Create a new GameInstance object with the updated name
        gameInstance = new GameInstance(gameInstance.getId(), "Updated Game Name", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.update(gameInstance);  // Update the GameInstance object

        // Check if the name was updated correctly
        assertEquals("Updated Game Name", gameInstance.getName());  // Verify if the name was updated
    }

    @Test
    public void testDoubleDelete() {
        // Create and insert a GameInstance object
        GameInstance gameInstance = new GameInstance("Game to Delete", GameMode.SINGLE, Collections.emptyList(), 5, true);
        gameInstance = gameInstanceDao.insert(gameInstance);

        // Try deleting the GameInstance object
        boolean deleted = gameInstanceDao.delete(gameInstance.getId());
        assertTrue(deleted);  // Check if the deletion was successful

        // Try deleting the same object again to test if it handles non-existent items correctly
        boolean deleteAgain = gameInstanceDao.delete(gameInstance.getId());
        assertFalse(deleteAgain);  // Ensure that deleting a non-existing item returns false

        // Verify that the object is no longer present in the list
        GameInstance deletedGameInstance = gameInstanceDao.findOne(gameInstance.getId());
        assertNull(deletedGameInstance);  // Check if the object is really deleted
    }
}