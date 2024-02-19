package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;


import java.util.Objects;

/**
 * Altar class represents an altar entity in the game world.
 * An altar gives a Yokimon to the player at the first interaction.
 */
public class Altar extends Entity {
    private AltarState state;
    private static final double RADIUS = 150;
    private static final double HITBOX_SIDE = 125;
    private static final Vector2 DIMENSIONS = new Vector2Impl(HITBOX_SIDE * 5 / 10, HITBOX_SIDE);

    private static final double DIMENSION_SCALE = 1.05;
    /**
     * Constructs an Altar object with the specified attributes.
     * @param pos The position of the Altar
     * @param messageHandler Message handler used to handle events
     */
    public Altar(final Position pos, final MessageHandler messageHandler) {
        super(pos, new RectangularHitbox(pos.getPosition(), DIMENSIONS),
                messageHandler, DIMENSIONS.scale(DIMENSION_SCALE), "");
        this.state = AltarState.POWERED;
    }

    /**
     * This method return a string with the Altar ResourceURL used to display his sprite in the view.
     * Returns a different image depending on the Altar state.
     * @return String
     */
    @Override
    protected String getResourceURL() {
        return "view/game/textures/altar_" + (state == AltarState.POWERED ? "full" : "empty") + ".png";
    }

    /**
     * Returns the current state of the Altar (if it's used or not).
     * @return altarState Current state of the Altar
     */
    public final AltarState getState() {
        return this.state;
    }

    /**
     * Updates the state of the Altar.
     */
    @Override
    public void update() {
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, pos -> {
            Objects.requireNonNull(pos.getPosition().getPosition(), "Position of the player invalid");

            if (this.state == AltarState.POWERED && pos.getPosition().isValid()
                    && pos.getPosition().inRadius(this.getPos(), RADIUS)) {

                    this.getMessageHandler().handle(PartySubmodule.class, party -> {
                    //party.addYokimon(this.getNewYokimon());
                    this.state = AltarState.USED;

                });

            }
        });
    }

    /**
     * Represents the state of the Altar (used or powered).
     */
    public enum AltarState {
        /**
         * If the altar is in this state will no longer give a yokimon.
         */
        USED,
        /**
         * If the altar is in this state is active and will give a yokimon.
         */
        POWERED
    }

}
