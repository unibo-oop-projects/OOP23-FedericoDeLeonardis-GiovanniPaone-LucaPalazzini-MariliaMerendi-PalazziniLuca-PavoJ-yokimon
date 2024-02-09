package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

/**
 * Basic version for Damage Calculator, that only takes into consideration
 * the two Yokimons' stats and the attack power.
 */
public final class BasicImplDmgCalculator implements DmgCalculator {
    @Override
    public int getDMG(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
        return (int) (attackingYokimon.getStat(Yokimon.Stats.ATK) * attack.attackPower()
                / attackedYokimon.getStat(Yokimon.Stats.DEF));
    }
}
