package at.aau.serg.exercises.backend.dao;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({PlayerDaoTest.class, GameInstanceDaoTest.class})
public class DaoTests {

}
