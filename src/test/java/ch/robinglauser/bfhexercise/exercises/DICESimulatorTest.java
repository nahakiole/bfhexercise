package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.DICESimulator;
import org.junit.Assert;
import org.junit.Test;

public class DICESimulatorTest {
    @Test
    public void throwDice() throws Exception {
        int[] results = {
                0,0,0,0,0,0
        };

        DICESimulator diceSimulator = new DICESimulator();
        Assert.assertNotEquals(7, diceSimulator.throwDice());
        Assert.assertNotEquals(0, diceSimulator.throwDice());
        for (int i = 0; i < 100; i++) {
            int result = diceSimulator.throwDice();
            results[result-1]++;
        }

        for (int result : results) {
            Assert.assertTrue(result > 0);
        }

    }

}