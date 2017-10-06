package ch.robinglauser.bfhexercise.exercises;

import java.awt.*;

public class RectangleUtils {

    /**
     * Get the perimeter of a rectangle.
     *
     * @param rectangle
     * @return perimeter of rectangle
     */
    public static int getPerimeter(Rectangle rectangle) {
        return (rectangle.height+rectangle.width)*2;
    }

    /**
     * Get the area of a rectangle
     *
     * @param rectangle
     * @return area of rectangle
     */
    public static int getArea(Rectangle rectangle) {
        return rectangle.height*rectangle.width;
    }

}
