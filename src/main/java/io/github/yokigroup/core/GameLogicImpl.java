package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl extends Thread {
    private final MessageHandler handler;
    private boolean running = true;

    public GameLogicImpl(ModelObserver view) {
        super();
        handler = new GameMessageHandler(view);
    }

    public MessageHandler getMessageHandler() {
        return handler;
    }

    @Override
    public void run() {
        gameLoop();
    }

    private void gameLoop() {
        while (running) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            handler.updateSubmodules();
        }
    }
}
