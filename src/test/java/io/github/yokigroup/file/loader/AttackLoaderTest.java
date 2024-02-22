package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AttackLoaderTest {
    private static AttackLoader loader;

    @BeforeEach
    void setUp() {
        loader = new AttackLoader();
    }

    @Test
    void load() {
        final int attackId = 1;
        final String expectedName = "Shadow Ball";
        final Color expectedColor = Color.BLACK;
        final Attack.Effect expectedEffect = Attack.Effect.NONE;
        final int expectedPower = 90;

        final Attack toTest = loader.load(attackId);

        assertEquals(expectedName, toTest.getName());
        assertEquals(expectedColor, toTest.getColor());
        assertEquals(expectedEffect, toTest.getEffectID());
        assertEquals(expectedPower, toTest.attackPower());
    }
}
