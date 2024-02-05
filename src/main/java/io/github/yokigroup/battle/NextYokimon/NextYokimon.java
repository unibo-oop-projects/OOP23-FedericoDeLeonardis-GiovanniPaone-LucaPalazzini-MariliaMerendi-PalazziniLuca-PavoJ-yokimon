package io.github.yokigroup.battle.NextYokimon;

import io.github.yokigroup.battle.Yokimon;

import java.util.List;
import java.util.Optional;

public abstract class NextYokimon {
    /**
     *
     * @param party the party from which it must be chosen the next one to fight, once the current one is defeated
     * @return the party member chosen
     */
    public abstract Optional<Yokimon> getNext(List<Yokimon> party);
}
