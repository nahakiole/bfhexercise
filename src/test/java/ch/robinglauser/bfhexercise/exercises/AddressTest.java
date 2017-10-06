package ch.robinglauser.bfhexercise.exercises;

import ch.robinglauser.bfhexercise.exercises.Address;
import org.junit.Assert;
import org.junit.Test;

public class AddressTest {
    @Test
    public void toStringTest() throws Exception {
        Address address = new Address("Niesenstrasse", "26C", "3600", "Thun");
        Assert.assertEquals("Niesenstrasse", address.getStreet());
        Assert.assertEquals("26C", address.getStreetNumber());
        Assert.assertEquals("3600", address.getPostalCode());
        Assert.assertEquals("Niesenstrasse 26C, 3600 Thun", address.toString());


    }

}