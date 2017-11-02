package ch.robinglauser.bfhexercise.game;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class Screen extends JPanel {

    public Vector<Drawable> elements = new Vector<>();

    public boolean test = false;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Iterator<Drawable> iterator = elements.iterator(); iterator.hasNext(); ) {
            Drawable element = iterator.next();
            g.setColor(Color.BLACK);
            element.draw(g);
        }
    }

    public void addDrawable(Drawable drawable){
        elements.add(drawable);
    }
}
