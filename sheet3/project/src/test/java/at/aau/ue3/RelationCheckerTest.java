package at.aau.ue3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelationCheckerTest {

    // Statement Coverage

    @Test
    public void checkRelation_input_0_0_return_0() {
        int result = RelationChecker.checkRelation(0, 0);
        Assertions.assertEquals(0, result, "Expected result for (0,0) is 0");
    }







}
