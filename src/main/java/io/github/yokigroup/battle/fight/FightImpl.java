package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import io.github.yokigroup.battle.xpcalculator.XPCalculator;
import io.github.yokigroup.battle.xpcalculator.DummyImplXPCalculator;

import java.util.LinkedList;
import java.util.List;

/**
 * The actual Fight implementation communicating with the Logic.
 */
public final class FightImpl implements Fight {

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;


    /* structures */
    private final XPCalculator xpCalc = new DummyImplXPCalculator();
    private final DmgCalculator dmgCalc = new BasicImplDmgCalculator();
    private final OpponentAI oppAI = new DummyImplOpponentAI(dmgCalc);
    private final NextYokimon nextYok = new DummyImplNextYokimon();


    /**
     * Boolean that triggers end of the fight.
     */
    private boolean isOver;

    /**
     * List to keep in store defeated Yokimons.
     */
    private final List<Yokimon> defeatedOpps = new LinkedList<>();


    /**
     * Builder to instantiate the fight through the Logic.
     * @param myYokimons my party
     * @param oppYokimons the opponent party
     */
    public FightImpl(final List<Yokimon> myYokimons, final List<Yokimon> oppYokimons) {
        this.myYokimons = myYokimons;
        this.oppYokimons = oppYokimons;
    }

    @Override
    public Success attack(final Attack myAttack) {

        int damage = dmgCalc.getDMG(currMyYokimon,currOppYokimon,myAttack);
        //currOppYokimon.removeHP(damage);

        if (!currOppYokimon.Active()) {         //active????????????
            oppYokimons.remove(currOppYokimon);
            defeatedOpps.add(currMyYokimon);

            if(!oppYokimons.isEmpty()) {
                currOppYokimon = nextYok.getNext(oppYokimons).get();
            }
            else {
                isOver = true;
                int xpGain = xpCalc.getXP(defeatedOpps);
                currMyYokimon.takeXP(xpGain);
            }
        }

        return null;
    }

    @Override
    public Success getAttacked() {                                                      ////missing
        return null;
    }

    @Override
    public boolean isOver() {
        return myYokimons.isEmpty() || oppYokimons.isEmpty();
    }

    @Override
    public boolean victory() {
        return oppYokimons.isEmpty();
    }

    @Override
    public int getXP(final Yokimon yokimon) {
        return xpCalc.getXP(defeatedOpps);
    }

    @Override
    public Yokimon getCurrentMyYokimon() {
        return currMyYokimon;
    }

    @Override
    public Yokimon getCurrentOpponent() {
        return currOppYokimon;
    }

    /* utilities to update Yokimons involved in fight */
    private void updateMyCurr() {
        nextYok.getNext(myYokimons);
    };
    private void updateOppCurr() {
        nextYok.getNext(oppYokimons);
    };
}
