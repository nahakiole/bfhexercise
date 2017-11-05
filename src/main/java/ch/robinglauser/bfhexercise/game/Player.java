package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener {


    private int VK_RIGHT;
    private int VK_LEFT;
    private int VK_JUMP;
    private int VK_PUNCH;

    public Player(int VK_RIGHT, int VK_LEFT, int VK_JUMP, Color color, int startx, int VK_PUNCH) {
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_JUMP = VK_JUMP;
        this.VK_PUNCH = VK_PUNCH;
        this.color = color;
        this.position.x = startx;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (health != 0) {
            if (e.getKeyCode() == this.VK_RIGHT) {
                punchDirection = true;
                acceleration = 4;
                rightpressed = true;
            }

            if (e.getKeyCode() == this.VK_PUNCH) {
                punching = true;
            }

            if (e.getKeyCode() == this.VK_LEFT) {
                punchDirection = false;
                acceleration = -4;
                leftpressed = true;
            }

            if (e.getKeyCode() == this.VK_JUMP && jumping == 0) {
                jumping = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == this.VK_PUNCH) {
            punching = false;
        }

        if (e.getKeyCode() == this.VK_RIGHT) {
            rightpressed = false;
            if (!leftpressed) {
                acceleration = 0;
            }
        }
        if (e.getKeyCode() == this.VK_LEFT) {
            leftpressed = false;
            if (!rightpressed) {
                acceleration = 0;
            }
        }
    }


}
