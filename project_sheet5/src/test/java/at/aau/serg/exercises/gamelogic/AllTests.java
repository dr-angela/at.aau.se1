package at.aau.serg.exercises.gamelogic;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({CliGameRunnerParameterTest.class, CliGameRunnerStateTest.class, CliGameRunnerRandomTest.class})
public class AllTests {

}
