package ch.robinglauser.bfhexercise.game;

import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

public class GameThread extends Thread {

    private Screen screen;

    private boolean paused = false;

    public Vector<Updateable> elements = new Vector<>();

    private long gameTime;

    public GameThread(Screen screen) {
        this.screen = screen;
    }

    public void addElement(Updateable element) {
        elements.add(element);
    }

    public long getGameTime() {
        return gameTime;
    }

    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;
        while (true) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }

            screen.repaint();

            for (Iterator<Updateable> iterator = elements.iterator(); iterator.hasNext(); ) {
                iterator.next().update(now);
            }

            try {
                gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {

            }
        }

    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
