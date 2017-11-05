package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public class Enemy extends Character  {

    double timesincelastpunch = 0;
    private int speed = 2;

    private final Player[] players;

    public Enemy(Player[] player) {
        this.position.x = (int) (Math.random()*600);
        this.players = player;
        this.eyes = Color.RED;
        this.speed = (int) (Math.random()*3)+1;
    }


    @Override
    public void update(double time) {
        Player player = players[0];
        if (players[0].position.distance(position) > players[1].position.distance(position) || players[0].health == 0){
            player = players[1];
        }
        if (players[1].health == 0){
            player = players[0];
        }

        if (player != null){
            int distanceX = (int) (player.position.getX()-position.getX());
            int distanceY = (int) (player.position.getY()-position.getY());
            if (distanceX < -20){
                punching = false;
                acceleration = -speed;
                punchDirection = false;
            }
            else if (distanceX > 20) {
                punching = false;
                acceleration = speed;
                punchDirection = true;
            }
            else {
                timesincelastpunch+=time;
                if (timesincelastpunch > 5){
                    punching = !punching;
                    timesincelastpunch = 0;
                }
                acceleration = 0;
            }
        }
        super.update(time);
    }
}
