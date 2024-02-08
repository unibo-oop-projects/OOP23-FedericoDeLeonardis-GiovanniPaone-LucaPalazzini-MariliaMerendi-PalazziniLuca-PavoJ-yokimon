package io.github.yokigroup.util;

/**
 * An implementation of the binding to a vector2 class.
 */
public class Vector2Impl implements Vector2 {
    private final org.dyn4j.geometry.Vector2 vector;

    /**
     *
     * @param x The first component of the vector.
     * @param y The second component of the vector.
     */
    public Vector2Impl(final double x, final double y) {
        vector = new org.dyn4j.geometry.Vector2(x, y);
    }

    /**
     * Creates a Vector2 with coordinates at 0.0.
     */
    public Vector2Impl() {
        this(0.0d, 0.0d);
    }

    @Override
    public final double getX() {
        return vector.x;
    }

    @Override
    public final double getY() {
        return vector.y;
    }

    @Override
    public final Vector2 plus(final Vector2 other) {
        return new Vector2Impl(this.getX() + other.getX(), this.getY() + other.getY());
    }

    @Override
    public final Vector2 minus(final Vector2 other) {
        return new Vector2Impl(this.getX() - other.getX(), this.getY() - other.getY());
    }

    @Override
    public final Vector2 times(final Vector2 other) {
        return new Vector2Impl(this.getX() * other.getX(), this.getY() * other.getY());
    }

    @Override
    public final Vector2 divide(final Vector2 other) {
        return new Vector2Impl(this.getX() / other.getX(), this.getY() / other.getY());
    }

    @Override
    public final Vector2 scale(final double value) {
        return new Vector2Impl(this.getX() * value, this.getY() * value);
    }

    @Override
    public final Vector2 normalize() {
        final org.dyn4j.geometry.Vector2 normalized = this.vector.getNormalized();
        return new Vector2Impl(normalized.x, normalized.y);
    }

    @Override
    public final double length() {
        return this.vector.getMagnitude();
    }

    @Override
    public final double dot(final Vector2 other) {
        return this.vector.dot(((Vector2Impl) other).getVector());
    }

    private org.dyn4j.geometry.Vector2 getVector() {
        return this.vector;
    }
}
