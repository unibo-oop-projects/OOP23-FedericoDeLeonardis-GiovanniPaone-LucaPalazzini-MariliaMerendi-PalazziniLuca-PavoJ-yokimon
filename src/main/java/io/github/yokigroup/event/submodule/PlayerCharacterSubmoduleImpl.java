package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.people.Player;

/**
 * Handles player updates.
 * @author Giovanni Paone
 */
public final class PlayerCharacterSubmoduleImpl extends PlayerCharacterSubmodule {
    private final Entity player;

    // FIXME Replace with proper implementation

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PlayerCharacterSubmoduleImpl(final MessageHandler handler) {
        super(handler);
        Vector2 playerPos = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() /2, (double) GameMap.TILE_DIMENSIONS.y() /2);
        this.player = new Player(new PositionImpl(playerPos), handler);
    }

    // TODO Change Direction reference
    @Override
    public void changeTile(final Direction dir) {
        handler().handle(GameMapSubmoduleImpl.class, s -> {
            s.getGameMap().movePlayerTileMapPosition(dir);
        });
    }

    @Override
    public Position getPosition() {
        return player.getPos();
    }

    @Override
    public Entity getPlayerEntity() {
        return player;
    }

    @Override
    public void movePlayerBy(final Vector2 delta) {
        player.setPos(
                new PositionImpl(player.getPos().getPosition().plus(delta))
        );
    }

}
