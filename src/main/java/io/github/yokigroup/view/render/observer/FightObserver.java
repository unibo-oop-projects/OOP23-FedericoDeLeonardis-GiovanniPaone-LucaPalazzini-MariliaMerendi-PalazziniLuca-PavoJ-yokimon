package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.render.RenderState;

public class FightObserver extends ViewObserver<Fight> {
    public FightObserver(final Painter painter, final DrawQueue drawQueue) {
        super(painter, drawQueue);
    }

    @Override
    public void update(final Fight lastArg, final Fight arg) {
        painter().setPaintState(RenderState.FIGHT);
        painter().changeDrawQueue(drawQueue());
    }
}
