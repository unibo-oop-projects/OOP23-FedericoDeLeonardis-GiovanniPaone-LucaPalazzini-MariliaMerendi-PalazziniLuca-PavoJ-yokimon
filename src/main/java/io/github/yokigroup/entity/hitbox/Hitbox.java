package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Geometry;

public interface Hitbox {

    /**
     * Checks if this hitbox is colliding with another
     * @param other the other hitbox to check.
     * @return true if the hitboxes are colliding.
     */
    boolean collidesWith(final Hitbox other);

    /**
     * Sets where the hitbox is located.
     * @param pos the positions of the hitbox.
     */
    void setPosition(final Pair<Float, Float> pos);

    /**
     *
     * @return the geometry of the hitbox object
     */
    Geometry getGeometry();
}
