package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class InputSubmodule extends InputSubmoduleAbs {
    private Set<Direction> moveEvents = new HashSet<>();
    private boolean clickedConfirmEvent = false;


    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs ModelObserver
     */
    public InputSubmodule(final MessageHandler handler, final ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    private boolean readDirEvent(final String keyText, final Consumer<Direction> ifPresent) {
        final Direction dir = switch (keyText.toLowerCase(Locale.ROOT)) {
            case "w" -> Direction.UP;
            case "a" -> Direction.LEFT;
            case "s" -> Direction.DOWN;
            case "d" -> Direction.RIGHT;
            default -> null;
        };
        if (dir != null) {
            synchronized (this) {
                ifPresent.accept(dir);
            }
            return true;
        }
        return false;
    }

    private boolean readConfirmationEvent(final String keyText, final Runnable ifPresent) {
        boolean confirmed = switch (keyText) {
            case "\n", " " -> true;
            default -> false;
        };
        if (confirmed) {
            synchronized (this) {
                ifPresent.run();
            }
        }
        return confirmed;
    }

    private <T> void cycleUntilTrue(List<Predicate<T>> predicates, T input) {
        for (var event : predicates) {
            if (event.test(input)) {
                return;
            }
        }
    }

    /**
     * Register a keyPress event to the submodule.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyPress(final String keyText) {
        cycleUntilTrue(
                List.of(
                        k -> this.readDirEvent(k, d -> moveEvents.add(d)),
                        k -> this.readConfirmationEvent(k, () -> clickedConfirmEvent = true)
                ),
                keyText
        );
    }

    /**
     * Register a keyRelease event to the submodule.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyRelease(final String keyText) {
        cycleUntilTrue(
                List.of(
                        k -> this.readDirEvent(k, d -> moveEvents.remove(d))
                ),
                keyText
        );
    }

    private Pair<Integer, Integer> sumPairs(final Pair<Integer, Integer> pairOne,
                                            final Pair<Integer, Integer> pairTwo) {
        Objects.requireNonNull(pairOne);
        Objects.requireNonNull(pairTwo);
        return new Pair<>(pairOne.x() + pairTwo.x(), pairOne.y() + pairTwo.y());
    }

    @Override
    protected void updateCode(double delta) {
        final double velocity = 52.;
        Pair<Integer, Integer> dirSum;
        synchronized (this) {
            dirSum = moveEvents.stream().map(Direction::getOffset).reduce(new Pair<>(0, 0), this::sumPairs);
        }
        final Vector2 moveOffset = new Vector2Impl(dirSum.x(), dirSum.y()).normalize().scale(delta * velocity);
        if (!moveOffset.equals(Vector2Impl.NULL_VECTOR)) {
            handler().handle(PlayerCharacterSubmodule.class, s -> {
                s.movePlayerBy(moveOffset);
            });
        }
        if (clickedConfirmEvent) {
            clickedConfirmEvent = false;
        }
    }
}
