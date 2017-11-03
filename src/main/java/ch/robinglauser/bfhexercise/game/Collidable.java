package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public interface Collidable {

    public Rectangle getBounds();

    public void onCollide(Collidable collider);

}
