package at.aau.ue3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static at.aau.ue3.RelationChecker.*;

public class RelationCheckerTest {

    // Statement Coverage #1 else -> else
    @Test
    public void checkRelation_input_0_0_return_0() {
        int result = checkRelation(0, 0);
        Assertions.assertEquals(0, result, "Expected result for (0,0) is 0");
    }
    // Statement Coverage #2 if -> if
    @Test
    public void checkRelation_input_10_17_return_34() {
        int result = checkRelation(10, 17);
        Assertions.assertEquals(34, result, "Expected result for (10,17) is 34");
    }

    // Branch Coverage #3 else -> if
    @Test
    public void checkRelation_input_26_5_return_31() {
        int result = RelationChecker.checkRelation(26, 5);
        Assertions.assertEquals(31, result, "Expected result for (26,5) is 31");
    }

    // Branch Coverage #4 if -> else
    @Test
    public void checkRelation_input_15_15_return_30() {

        int result = checkRelation(15, 15);
        Assertions.assertEquals(30, result, "Expected result for (15,15) is 30");
    }

}
