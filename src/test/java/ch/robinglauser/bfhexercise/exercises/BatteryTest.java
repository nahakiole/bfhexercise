package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.Battery;
import org.junit.Assert;
import org.junit.Test;

public class BatteryTest {
    @Test
    public void drain() throws Exception {
        Battery battery = new Battery(100.0);
        battery.drain(29.9);
        Assert.assertEquals(70.1, battery.getCurCapacity(), 0.1);
        battery.charge();
        Assert.assertEquals(100, battery.getCurCapacity(), 0.1);
    }

}