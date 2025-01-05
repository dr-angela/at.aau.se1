package at.aau.serg.exercises.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({GameServiceImplIntegrationTest.class, GameServiceImplUnitTest.class})
public class GameServiceTests {
    @Test
    public void test() {
    }
}
