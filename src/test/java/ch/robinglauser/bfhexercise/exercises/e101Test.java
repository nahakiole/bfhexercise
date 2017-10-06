package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.e101;
import org.junit.Assert;
import org.junit.Test;

public class e101Test {

    @Test
    public void name() throws Exception {
        String[] teststring = {
          "Abcd",
          "Test",
          "sdfdsfsdf"
        };
        e101 e101 = new e101();
        int length = e101.getLongestStringLength(teststring);
        Assert.assertEquals(length, 9);
    }
}