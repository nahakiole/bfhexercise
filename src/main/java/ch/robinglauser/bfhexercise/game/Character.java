package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyListener;

public class Character implements Drawable, Updateable, Collidable {

    public static final int jumpHeight = 12;

    protected Color color;
    protected Color skin = new Color(212, 161, 40);
    Color eyes = Color.BLUE;

    int jumping = 0;
    boolean leftpressed = false;
    boolean rightpressed = false;
    double acceleration = 0;
    double walkanimation = 0;
    boolean punching = false;
    boolean punchDirection = false;

    int health = 100;

    Point position = new Point();


    public Point[] getJump(int height) {
        Point[] jump = new Point[height * 2 + 1];
        for (int i = -height; i <= height; i++) {
            jump[i + height] = new Point(0, i * 2);
        }
        return jump;
    }




    @Override
    public void draw(Graphics paramGraphics) {

        if (health <= 0) {
            paramGraphics.setColor(Color.BLACK);
            paramGraphics.fillRect((int) (position.x), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 16, 30);
            paramGraphics.setColor(Color.WHITE);
            paramGraphics.fillRect((int) (position.x)+7, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 24)), 2, 14);
            paramGraphics.fillRect((int) (position.x)+4, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 8, 2);
            return;
        }

        paramGraphics.setColor(Color.BLACK);
        if (health < 30) {
            paramGraphics.setColor(Color.RED);
        }
        paramGraphics.drawString(Integer.toString(health), (int) position.x - 3, (int) (position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 50)));

        paramGraphics.setColor(skin);
        //Legs
        paramGraphics.fillRect((int) (position.x + walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 5, 20);
        paramGraphics.fillRect((int) (position.x + 10 - walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 5, 20);

        //Upper Body
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 15, 20);

        //Head
        paramGraphics.fillOval((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 45)), 15, 15);

        //Shoulder
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 20 - ((int) walkanimation / 2), 4);


        if (punching) {
            //Arms
            if (punchDirection){
                paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
                paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 14, 3);
            }
            else {
                paramGraphics.fillRect((int) position.x - 14 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 14, 3);
                paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
            }


        } else {
            //Arms
            paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
            paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
        }

        paramGraphics.setColor(color);
        //Legs
        paramGraphics.fillRect((int) (position.x + walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 15)), 5, 10);
        paramGraphics.fillRect((int) (position.x + 10 - walkanimation), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 15)), 5, 10);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 15, 10);

        //Shirt
        paramGraphics.setColor(Color.WHITE);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 15, 12);
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 5);
        paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 5);
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 20 - ((int) walkanimation / 2), 4);

        //Eyes
        paramGraphics.fillOval((int) position.x + 3, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 40)), 5, 5);
        paramGraphics.fillOval((int) position.x + 8, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 40)), 5, 5);

        paramGraphics.setColor(eyes);
        paramGraphics.fillOval((int) position.x + 4 + ((int) acceleration / 4), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 39)), 3, 3);
        paramGraphics.fillOval((int) position.x + 9 + ((int) acceleration / 4), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 39)), 3, 3);

        //Hat
        paramGraphics.setColor(color);
        paramGraphics.fillArc(position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 48)), 15, 13, 0, 180);

    }

    @Override
    public void update(double time) {
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
        int width = 25;
        int punchingcorreection = 0;
        if (punching) {
            width += 10;
            if (!punchDirection){
                punchingcorreection = -10;
            }
        }

        return new Rectangle(position.x-5 + punchingcorreection, (position.y + 480 - (Stage.height + 100)), width, 60);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Character) {
            Character player = (Character) collider;
            if (player.punching) {
                health = health - 1;
                if (health < 0) {
                    health = 0;
                }
            }
        }
    }
}
