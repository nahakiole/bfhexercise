package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.SavingsAccount;
import org.junit.Assert;
import org.junit.Test;

public class SavingsAccountTest {

    @Test
    public void addInterest() throws Exception {
        SavingsAccount savingsAccount = new SavingsAccount(900, 2.5);
        for (int i = 0; i < 3; i++) {
            savingsAccount.addInterest();
        }
        Assert.assertEquals(969.20, savingsAccount.getBalance(), 0.1);

    }

}