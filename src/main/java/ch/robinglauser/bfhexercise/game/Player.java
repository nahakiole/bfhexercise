package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Drawable, KeyListener, Updateable, Collidable {

    public static final int jumpHeight = 12;

    private int VK_RIGHT;
    private int VK_LEFT;
    private int VK_JUMP;
    private int VK_PUNCH;
    private Color color;
    private Color skin = new Color(212, 161, 40);
    private Color hair = new Color(110, 59, 23);

    int jumping = 0;
    boolean leftpressed = false;
    boolean rightpressed = false;
    double acceleration = 0;
    double walkanimation = 0;
    boolean punching = false;
    boolean punchDirection = false;

    int health = 100;

    Point position = new Point();

    public Player(int VK_RIGHT, int VK_LEFT, int VK_JUMP, Color color, int startx, int VK_PUNCH) {
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_JUMP = VK_JUMP;
        this.VK_PUNCH = VK_PUNCH;
        this.color = color;
        this.position.x = startx;
    }


    public Point[] getJump(int height) {
        Point[] jump = new Point[height * 2 + 1];
        for (int i = -height; i <= height; i++) {
            jump[i + height] = new Point(0, i * 2);
        }
        return jump;
    }


    @Override
    public void draw(Graphics paramGraphics) {

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
            if (jumping > 0){

            }
            else {
                //Arms
                if (punchDirection){
                    paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
                    paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 14, 3);
                }
                else {
                    paramGraphics.fillRect((int) position.x - 14 + ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 14, 3);
                    paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 30)), 3, 16);
                }
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

        paramGraphics.setColor(Color.BLUE);

        paramGraphics.fillOval((int) position.x + 4 + ((int) acceleration / 4), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 39)), 3, 3);
        paramGraphics.fillOval((int) position.x + 9 + ((int) acceleration / 4), (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 39)), 3, 3);

        //Hat
        paramGraphics.setColor(color);
        paramGraphics.fillArc(position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 48)), 15, 13, 0, 180);

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
        return new Rectangle(position.x, position.y, 40, 60);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Player) {
            Player player = (Player) collider;
            if (player.punching) {
                health = health - 1;
                if (health < 0) {
                    health = 0;
                }
            }
            //player.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        }
    }
}
