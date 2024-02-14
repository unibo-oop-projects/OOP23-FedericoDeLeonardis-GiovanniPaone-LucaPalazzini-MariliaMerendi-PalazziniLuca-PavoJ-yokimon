package io.github.yokigroup.battleTest;

import java.util.List;
import io.github.yokigroup.battle.xpcalculator.FullImplXPCalculator;
import io.github.yokigroup.battle.xpcalculator.XPCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.xpcalculator.DummyImplXPCalculator;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for XP Calculator.
 */
public final class XPCalcTest {

    private static final int EXP_VAL_DUMMY1 = 0;
    private static final int EXP_VAL_DUMMY2 = 300;
    private static final int EXP_VAL_DUMMY3 = 200;
    private static final int EXP_VAL_FULL1 = 0;
    private static final int EXP_VAL_FULL2 = 330;
    private static final int EXP_VAL_FULL3 = 220;
    private static final int RAND_LEVEL = 12;

    private static Yokimon y1, y2, y3;
    private final YokimonLoader yokimonLoader = new YokimonLoader();

    /**
     * Initialises some Yokimons meant for testing.
     */
    @BeforeEach
    public void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(2);
        y3 = yokimonLoader.load(3);
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        XPCalculator toTest = new DummyImplXPCalculator();

        assertEquals(EXP_VAL_DUMMY1, toTest.getXP(List.of()));
        assertEquals(EXP_VAL_DUMMY2, toTest.getXP(List.of(y1, y2, y3)));
<<<<<<< HEAD
        assertEquals(EXP_VAL_DUMMY3, toTest.getXP(List.of(y1, y2)));

=======
        assertNotEquals(EXP_VAL_DUMMY3, toTest.getXP(List.of(y1, y2)));
>>>>>>> master
    }

    /**
     * Testing full implementation.
     */
    @Test public void testFullImpl() {
        XPCalculator toTest = new FullImplXPCalculator();

        assertEquals(EXP_VAL_FULL1, toTest.getXP(List.of()));
        assertEquals(EXP_VAL_FULL2, toTest.getXP(List.of(y1, y2, y3)));
        assertEquals(EXP_VAL_FULL3, toTest.getXP(List.of(y1, y2)));

        y2.setLevel(RAND_LEVEL);
        assertNotEquals(EXP_VAL_FULL3, toTest.getXP(List.of(y1, y2)));
    }
}
