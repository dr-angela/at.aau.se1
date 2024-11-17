package at.aau.ue3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static at.aau.ue3.RelationChecker.*;

public class RelationCheckerTest {

    // Statement Coverage

    @Test
    public void checkRelation_input_0_0_return_0() {
        int result = checkRelation(0, 0);
        Assertions.assertEquals(0, result, "Expected result for (0,0) is 0");
    }







}
