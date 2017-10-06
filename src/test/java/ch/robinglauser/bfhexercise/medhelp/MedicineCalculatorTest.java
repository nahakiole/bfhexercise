package ch.robinglauser.bfhexercise.medhelp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

public class MedicineCalculatorTest {

    @Test
    public void getGFT() throws Exception {
        Assert.assertEquals(44.6, MedicineCalculator.getRenalFunction(30,70, 2.4, MedicineCalculator.Gender.MAN), 0.1);
    }


    @Test
    public void getAge() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime(2017,
                9, 25, 0, 0).getMillis());

        Assert.assertEquals(22,
                MedicineCalculator.getAge(new DateTime(1995,
                        3, 24, 0, 0)));

        Assert.assertEquals(21,
                MedicineCalculator.getAge(new DateTime(1995,
                        10, 24, 0, 0)));
    }

    @Test
    public void getBirthDateWithNaegeleMethod() throws Exception {
        DateTime expectedBirthdate = new DateTime(2012,
                1, 14, 0, 0);
        DateTime birthdate = MedicineCalculator.getBirthDateWithNaegeleMethod(new DateTime(2011,
                4, 7, 0, 0));

        DateTimeFormatter fmt = DateTimeFormat.forPattern("DD.MM.Y");
        Assert.assertEquals(fmt.print(expectedBirthdate),fmt.print(birthdate));
    }

    @Test
    public void getBirthDate() throws Exception {
        DateTime lastDay = new DateTime(2010,
                11, 21, 0, 0);
        DateTime birthday = new DateTime(2011,
                8, 28, 0, 0);
        Assert.assertEquals(birthday.getMillis(), MedicineCalculator.getBirthDate(lastDay).getMillis());
    }

    @Test
    public void getIdealWeight() throws Exception {
        Assert.assertEquals(76.5, MedicineCalculator.getIdealWeight(185, MedicineCalculator.Gender.MAN), 0.001);
        Assert.assertEquals(68, MedicineCalculator.getIdealWeight(185, MedicineCalculator.Gender.WOMAN), 0.001);
    }

    @Test
    public void getNormalWeight() throws Exception {
        Assert.assertEquals(85, MedicineCalculator.getNormalWeight(185), 0.001);
        Assert.assertEquals(65, MedicineCalculator.getNormalWeight(165), 0.001);
    }

    @Test
    public void getBodyMassIndex() throws Exception {
        Assert.assertEquals(20.4528, MedicineCalculator.getBodyMassIndex(70, 185), 0.001);
    }

    @Test
    public void getBodySurface() throws Exception {
        Assert.assertEquals(1.802105068596924, MedicineCalculator.getBodySurface(60, 185, 22), 0.001);
        Assert.assertEquals(1.52777777, MedicineCalculator.getBodySurface(55, 100, 10), 0.001);
    }

}