package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Drawable, KeyListener, Updateable {

    double  x = 0;
    double  y = 0;
    double acceleration = 0;

    @Override
    public void draw(Graphics paramGraphics) {
        paramGraphics.fillRect((int) x,(int) y+50 ,50,50);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            acceleration = Math.min(5, acceleration+1);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            acceleration = Math.max(-5, acceleration-1);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("space");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void update(long time) {
        x+= acceleration;
    }
}
