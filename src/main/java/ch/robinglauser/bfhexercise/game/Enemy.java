package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public class Enemy implements Drawable, Updateable  {

    Point position = new Point(100,-300);
    Color color = Color.BLUE;
    Player[] players = new Player[2];

    public Enemy(Player[] player) {
        this.players = player;
    }

    @Override
    public void draw(Graphics paramGraphics) {
        paramGraphics.setColor(color);
        paramGraphics.fillOval((int) position.x, (int) ((int) position.y + paramGraphics.getClipBounds().getHeight() - (Stage.height + 20)), 20, 20);
    }

    @Override
    public void update(double time) {
        Player player = players[0];
        if (players[0].position.distance(position) > players[1].position.distance(position)){
            player = players[1];
        }

        if (player != null){
            int distanceX = (int) (player.position.getX()-position.getX());
            int distanceY = (int) (player.position.getY()-position.getY());
            if (distanceX == 0 && distanceY == 0){
                position.x = (int) (Math.random()*100)+30;
                position.y = (int) (Math.random()*100)-100;
            }

            if (distanceX < 0){
                position.x = position.x-1;
            }
            else {
                position.x = position.x+1;
            }
            if (distanceY < 0){
                position.y--;
            }
            else {
                position.y++;
            }
        }

    }
}
