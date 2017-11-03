package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Iterator;
import java.util.Vector;

public class InputHandler implements KeyEventPostProcessor {

    private Vector<KeyListener> keyListeners = new Vector<>();

    void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }


    @Override
    public boolean postProcessKeyEvent(KeyEvent e) {
        for (Iterator<KeyListener> iterator = keyListeners.iterator(); iterator.hasNext(); ) {
            KeyListener listener = iterator.next();
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                listener.keyReleased(e);
            }
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                listener.keyPressed(e);
            }
            if (e.getID() == KeyEvent.KEY_TYPED) {
                listener.keyTyped(e);
            }
        }
        return false;
    }
}