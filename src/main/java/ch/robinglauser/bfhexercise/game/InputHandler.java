package ch.robinglauser.bfhexercise.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

public class InputHandler implements KeyListener {

    private Vector<KeyListener> keyListeners = new Vector<>();

    void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (Iterator<KeyListener> iterator = keyListeners.iterator(); iterator.hasNext(); ) {
            iterator.next().keyTyped(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        for (Iterator<KeyListener> iterator = keyListeners.iterator(); iterator.hasNext(); ) {
            iterator.next().keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Iterator<KeyListener> iterator = keyListeners.iterator(); iterator.hasNext(); ) {
            iterator.next().keyReleased(e);
        }
    }
}