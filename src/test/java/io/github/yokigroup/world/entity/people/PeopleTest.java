package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeopleTest {
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        MessageHandler messageHandler = new GameMessageHandler();
    }
    @Test
    void setDirection() {
        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Enemy en = new Enemy(playerPos, messageHandler);
        Vector2 vector = new Vector2Impl(1, 0);
        en.setDirection(vector);
        assertEquals(People.Direction.RIGHT, en.getDirection());
        Vector2 vector2 = new Vector2Impl(-1, 0);
        en.setDirection(vector2);
        assertEquals(People.Direction.LEFT, en.getDirection());
        Vector2 vector3 = new Vector2Impl(-1, 1);
        en.setDirection(vector3);
        assertEquals(People.Direction.DOWN, en.getDirection());
        Vector2 vector4 = new Vector2Impl(-1, -1);
        en.setDirection(vector4);
        assertEquals(People.Direction.UP, en.getDirection());
    }
}
