package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public class Stage implements Drawable, Updateable {


    @Override
    public void draw(Graphics paramGraphics) {
        paramGraphics.fillRect(0, (int) (paramGraphics.getClipBounds().getSize().getHeight()-50) , (int) paramGraphics.getClipBounds().getSize().getWidth(),50);
    }

    @Override
    public void update(long time) {

    }
}
