package io.github.yokigroup.battleTest;

import java.util.List;
//import io.github.yokigroup.battle.xpcalculator.XPCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
//import io.github.yokigroup.battle.xpcalculator.DummyImplXPCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for XP Calculator.
 */
public class XPCalcTest {

    private static final int EXP_VAL_DUMMY1 = 0;
    private static final int EXP_VAL_DUMMY2 = 300;
    private static final int EXP_VAL_DUMMY3 = 500;

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
//        XPCalculator toTest = new DummyImplXPCalculator();

        /*      //TODO INST. YOKIMON
        Yokimon y1 = new YokimonImpl("Blue");
        Yokimon y2 = new YokimonImpl("Red");
        Yokimon y3 = new YokimonImpl("Yellow");

        assertEquals(EXP_VAL_DUMMY1, toTest.getXP(List.of()));
        assertEquals(EXP_VAL_DUMMY2, toTest.getXP(List.of(y1, y2, y3)));
        assertNotEquals(EXP_VAL_DUMMY3, toTest.getXP(List.of(y1, y2)));
         */

    }
}
