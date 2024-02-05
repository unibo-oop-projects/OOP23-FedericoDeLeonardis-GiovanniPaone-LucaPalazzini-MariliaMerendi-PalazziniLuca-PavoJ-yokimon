package io.github.yokigroup.battle.DMGcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

public interface DMGcalculator {

    /**
     * Calculates the actual damage provided by the attack
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return total damage points (to subtract from attacked Yokimon's HP)
     */
    int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack);
}
