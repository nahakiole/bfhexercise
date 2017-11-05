package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public class Enemy extends Character  {

    double timesincelastpunch = 0;

    private final Player[] players;

    public Enemy(Player[] player) {
        this.position.x = 50;
        this.players = player;
        this.eyes = Color.RED;
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
            if (distanceX < -20){
                punching = false;
                acceleration = -2;
                punchDirection = false;
            }
            else if (distanceX > 20) {
                punching = false;
                acceleration = 2;
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
