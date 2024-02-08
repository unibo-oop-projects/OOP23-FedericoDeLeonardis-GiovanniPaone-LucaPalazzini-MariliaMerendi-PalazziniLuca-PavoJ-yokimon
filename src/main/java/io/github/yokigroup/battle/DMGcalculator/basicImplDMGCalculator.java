package io.github.yokigroup.battle.DMGcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

public class basicImplDMGCalculator implements DMGCalculator {
    @Override
    public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        return (int)(attackingYokimon.getStat(Yokimon.Stats.ATK)*attack.attackPower()
                /attackedYokimon.getStat(Yokimon.Stats.DEF));
    }
}
