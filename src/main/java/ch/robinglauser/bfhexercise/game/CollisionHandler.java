package ch.robinglauser.bfhexercise.game;

import java.util.Iterator;
import java.util.Vector;

public class CollisionHandler implements Updateable {
    private Vector<Collidable> collidables = new Vector<>();

    void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    @Override
    public void update(long time) {
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


}
