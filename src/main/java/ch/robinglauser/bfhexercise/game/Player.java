package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Drawable, KeyListener, Updateable {
    double acceleration = 0;

    Point position = new Point();

    int jumping = 0;

    boolean leftpressed = false;
    boolean rightpressed = false;

    private int VK_RIGHT;
    private int VK_LEFT;
    private int VK_JUMP;
    private Color color;

    public Player(int VK_RIGHT, int VK_LEFT, int VK_JUMP, Color color) {
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_JUMP = VK_JUMP;
        this.color = color;
    }

    public static final int jumpHeight = 20;


    public Point[] getJump(int height) {
        Point[] jump = new Point[height * 2 + 1];
        for (int i = -height; i <= height; i++) {
            jump[i + height] = new Point(0, i);
        }
        return jump;
    }


    @Override
    public void draw(Graphics paramGraphics) {
        paramGraphics.setColor(color);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 20, 20);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == this.VK_RIGHT) {
            acceleration = 4;
            rightpressed = true;
        }

        if (e.getKeyCode() == this.VK_LEFT) {
            acceleration = -4;
            leftpressed = true;
        }

        if (e.getKeyCode() == this.VK_JUMP && jumping == 0) {
            jumping = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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

    @Override
    public void update(long time) {
        position.x += acceleration;
        if (jumping > 0 && jumping - 1 < getJump(jumpHeight).length) {
            position.translate(getJump(jumpHeight)[jumping - 1].x, getJump(jumpHeight)[jumping - 1].y);
            jumping++;
        }
        if (jumping - 1 == getJump(jumpHeight).length) {
            jumping = 0;
        }
    }
}
