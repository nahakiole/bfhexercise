package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Drawable, KeyListener, Updateable, Collidable {

    public static final int jumpHeight = 20;

    private int VK_RIGHT;
    private int VK_LEFT;
    private int VK_JUMP;
    private Color color;

    int jumping = 0;
    boolean leftpressed = false;
    boolean rightpressed = false;
    double acceleration = 0;
    double walkanimation = 0;

    Point position = new Point();

    public Player(int VK_RIGHT, int VK_LEFT, int VK_JUMP, Color color) {
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_JUMP = VK_JUMP;
        this.color = color;
    }


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

        paramGraphics.fillRect((int) (position.x + walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 5, 20);

        paramGraphics.fillRect((int) (position.x + 10 - walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 5, 20);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 15, 20);

        paramGraphics.fillOval((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 45)), 15, 15);

        paramGraphics.fillRect((int) position.x - 4, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 20, 4);

        paramGraphics.fillRect((int) position.x - 4, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
        paramGraphics.fillRect((int) position.x + 16, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
        } else {
            if (acceleration != 0) {
                walkanimation = (walkanimation + 0.4) % 6;
            } else {
                walkanimation = 0;
            }
        }
        if (jumping - 1 == getJump(jumpHeight).length) {
            jumping = 0;
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, 20, 50);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Player) {
            Player player = (Player) collider;
            player.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        }
    }
}
