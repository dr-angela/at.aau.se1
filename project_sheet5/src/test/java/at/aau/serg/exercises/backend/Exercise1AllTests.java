package at.aau.serg.exercises.backend;

import at.aau.serg.exercises.backend.dao.GameInstanceDaoTest;
import at.aau.serg.exercises.backend.dao.PlayerDaoTest;
import at.aau.serg.exercises.backend.service.GameServiceImplIntegrationTest;
import at.aau.serg.exercises.backend.service.GameServiceImplUnitTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({PlayerDaoTest.class, GameInstanceDaoTest.class, GameServiceImplIntegrationTest.class, GameServiceImplUnitTest.class})
public class Exercise1AllTests {

}
