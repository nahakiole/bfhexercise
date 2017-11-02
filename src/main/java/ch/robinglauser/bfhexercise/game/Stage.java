package ch.robinglauser.bfhexercise.game;

import java.awt.*;

public class Stage implements Drawable, Updateable {
    public static final int height = 50;

    @Override
    public void draw(Graphics paramGraphics) {
        paramGraphics.fillRect(0, (int) (paramGraphics.getClipBounds().getSize().getHeight()-height) , (int) paramGraphics.getClipBounds().getSize().getWidth(),height);
    }

    @Override
    public void update(long time) {

    }
}
