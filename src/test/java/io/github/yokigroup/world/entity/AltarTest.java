package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;

import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmoduleImpl;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmoduleImpl;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {
    private TestMessageHandler testMeg;
    private static final double DISTANCE = 40;

    private static class TestMessageHandler extends GameMessageHandler {
        public static class TestSubmodule extends GameMapSubmodule {
            Vector2 v = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - DISTANCE,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2);
            TileBuilder tile = new TileBuilderImpl(0).addEntity(TileBuilder.EntityType.ALTAR, new PositionImpl(v));
            GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0,0)).build(this.handler());

            public TestSubmodule(MessageHandler handler) {
                super(handler);
            }

            @Override
            public GameMap getGameMap() {
                return map;
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return map.getPlayerTile().getHitboxes();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return map.getPlayerTile().getEntities();
            }
        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return Set.of(
                    PlayerCharacterSubmoduleImpl.class,
                    TestSubmodule.class,
                    PartySubmoduleImpl.class
            );
        }
    }

    @BeforeEach
    void setUp() {
        testMeg = new TestMessageHandler();
    }
    @Test
    void altarTest() {

        testMeg.handle(TestMessageHandler.TestSubmodule.class, map -> {
           for ( Entity entity : map.getEntitiesOnCurrentTile() ) {

               if (entity instanceof Altar altar) {

                   testMeg.handle(PlayerCharacterSubmoduleImpl.class, player -> {
                        assertEquals(Altar.AltarState.POWERED,  altar.getState());
                        altar.update();
                        assertEquals(Altar.AltarState.USED,  altar.getState());


                   });
               }
           }
        });

    }

}
