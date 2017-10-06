package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.RectangleUtils;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class RectangleUtilsTest {

    @Test
    public void getPerimeter() throws Exception {
        Rectangle rectangle = new Rectangle(0,0,30,20);
        Assert.assertEquals(RectangleUtils.getPerimeter(rectangle), 100);
    }


    @Test
    public void getArea() throws Exception {
        Rectangle rectangle = new Rectangle(0,0,30,20);
        Assert.assertEquals(RectangleUtils.getArea(rectangle), 600);
    }

}