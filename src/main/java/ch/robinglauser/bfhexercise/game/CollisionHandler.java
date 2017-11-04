package ch.robinglauser.bfhexercise.game;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class CollisionHandler implements Updateable, Drawable {
    private Vector<Collidable> collidables = new Vector<>();

    void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    @Override
    public void update(double time) {
        for (Iterator<Collidable> iterator = collidables.iterator(); iterator.hasNext(); ) {
            Collidable collidable = iterator.next();
            for (Iterator<Collidable> innerIterator = collidables.iterator(); innerIterator.hasNext(); ) {
                Collidable collidable2 = innerIterator.next();
                if (collidable2 != collidable && collidable.getBounds().intersects(collidable2.getBounds())){
                    collidable.onCollide(collidable2);
                }
            }
        }
    }


    @Override
    public void draw(Graphics paramGraphics) {
        for (Iterator<Collidable> iterator = collidables.iterator(); iterator.hasNext(); ) {
            Collidable collidable = iterator.next();
            Rectangle rectangle = collidable.getBounds();
            paramGraphics.setColor(Color.RED);
            paramGraphics.drawRect(rectangle.x,rectangle.y,rectangle.width, rectangle.height);
        }
    }
}
