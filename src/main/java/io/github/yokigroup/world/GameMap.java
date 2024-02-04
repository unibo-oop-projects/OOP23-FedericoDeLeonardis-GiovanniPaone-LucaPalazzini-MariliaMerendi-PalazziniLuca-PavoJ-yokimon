package io.github.yokigroup.world;

import io.github.yokigroup.world.tile.Tile;
import javafx.util.Pair;

/**
 * A map containing tiles the player can traverse on.
 */
public interface GameMap {
    /**
     *
     * @param position The world position to take the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(final Pair<Integer, Integer> position);

    /**
     *
     * @return The player's world coordinates (the tile the player's on)
     */
    Pair<Integer, Integer> getPlayerWorldPosition();
}
