package io.github.yokigroup.util;

/**
 * Implementation of the MutablePair interface binding.
 */
public class MutablePairImpl implements MutablePair{
    private double x;
    private double y;

    public MutablePairImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public MutablePair getPair() {
        return this;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setPair(MutablePair newPair) {
        this.x = newPair.getX();
        this.y = newPair.getY();
    }

    @Override
    public void addVector(Vector2 vector) {
        this.x+=(float)vector.getX();
        this.y+=(float)vector.getY();
    }
}
