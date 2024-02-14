package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.event.submodule.SubmoduleMapImpl;
import io.github.yokigroup.util.MutablePairImpl;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.entity.Altar;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Enemy class represents an enemy entity in the game world.
 * It provides methods to manage and manipulate enemy entities.
 */
public class Enemy extends People {
    /**
     * This value represent the maximum distance at which the player will be in sight
     */
    private static final double RADIUS_PLAYER = 6.00;
    /**
     * This value represent the maximum distance at which the enemy will
     * go from his initial pos
     */
    private static final double RADIUS_INITIAL_POS = 5.00;
    private State state;
    private WeightedPoolImpl<Direction> directionWeightedPool;
    /**
     * Constructs an Enemy object with the specified attributes.
     * @param pos The position of the Enemy
     * @param hitBox The hitBox of the Enemy
     * @param party The party of Yokimon belonging to the Enemy
     */
    public Enemy(Position pos, Hitbox hitBox, List<Yokimon> party, MessageHandler messageHandler) {
        super(pos, hitBox, party, messageHandler);
        this.state = State.WANDER;

    }

    /**
     * Represents the state of the Enemy (wander or follow).
     */
    public enum State {
        WANDER,
        FOLLOW
    }
    /**
     * Logic calls this method when the player is too close to the enemy.
     *
     */
    public void follow() {

    }

    /**
     * method used to randomize the direction where the enemy will go when
     * is wandering.
     * @return Vector2
     */
    private Vector2 wander(){
        WeightedPoolImpl<Direction> directionWeightedPool = new WeightedPoolImpl<>();

        Stream.of(Direction.values())
                .filter(dir -> this.initialPos.inRadius(this.getPos().testTovePosition(dir.get()), RADIUS_INITIAL_POS))
                .filter(dir -> this.getPos().testTovePosition(dir.get()).isValid())
                .forEach(dir -> directionWeightedPool.addElement(dir, 0.1f));

           return directionWeightedPool.getRandomizedElement().get();

    }

    /**
     * return a new position given a vector, checking the hitBox of all the
     * entity in the tile
     * @param vector vector given
     * @return A new position
     */
    private Position move(Vector2 vector){
        //TODO
        return new PositionImpl(new MutablePairImpl(vector.getX(), vector.getY()));
    }
    /**
     * Updates the state of the Enemy (switches between wander and follow).
     *
     */
    @Override
    public void update() {
        if(!this.active){
            return;
        }
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, pos -> {
            Objects.requireNonNull(pos.getPosition().getPosition(), "Position of the player invalid");
            if(pos.getPosition().isValid() && this.getPos().inRadius(pos.getPosition(), RADIUS_PLAYER)) {
                this.state = State.FOLLOW;
            }
            else {
                this.state = State.WANDER;
            }
            if(this.state == State.WANDER) {

            }

        });

    }
}
