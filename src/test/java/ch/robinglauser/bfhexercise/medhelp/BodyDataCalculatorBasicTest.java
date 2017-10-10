package ch.robinglauser.bfhexercise.medhelp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

public class BodyDataCalculatorBasicTest {

    @Test
    public void getGFT() throws Exception {
        Assert.assertEquals(44.6, BodyDataCalculatorBasic.getRenalFunction(30,70, 2.4, BodyDataCalculatorBasic.Gender.MAN), 0.1);
    }


    @Test
    public void getAge() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime(2017,
                9, 25, 0, 0).getMillis());

        Assert.assertEquals(22,
                BodyDataCalculatorBasic.getAge(new DateTime(1995,
                        3, 24, 0, 0)));

        Assert.assertEquals(21,
                BodyDataCalculatorBasic.getAge(new DateTime(1995,
                        10, 24, 0, 0)));
    }

    @Test
    public void getBirthDateWithNaegeleMethod() throws Exception {
        DateTime expectedBirthdate = new DateTime(2012,
                1, 14, 0, 0);
        DateTime birthdate = BodyDataCalculatorBasic.getBirthDateWithNaegeleMethod(new DateTime(2011,
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
        Assert.assertEquals(birthday.getMillis(), BodyDataCalculatorBasic.getBirthDate(lastDay).getMillis());
    }

    @Test
    public void getIdealWeight() throws Exception {
        Assert.assertEquals(76.5, BodyDataCalculatorBasic.getIdealWeight(185, BodyDataCalculatorBasic.Gender.MAN), 0.001);
        Assert.assertEquals(68, BodyDataCalculatorBasic.getIdealWeight(185, BodyDataCalculatorBasic.Gender.WOMAN), 0.001);
    }

    @Test
    public void getNormalWeight() throws Exception {
        Assert.assertEquals(85, BodyDataCalculatorBasic.getNBW(185), 0.001);
        Assert.assertEquals(65, BodyDataCalculatorBasic.getNBW(165), 0.001);
    }

    @Test
    public void getBodyMassIndex() throws Exception {
        Assert.assertEquals(20.4528, BodyDataCalculatorBasic.getBMI(70, 185), 0.001);
    }

    @Test
    public void getBodySurface() throws Exception {
        Assert.assertEquals(1.802105068596924, BodyDataCalculatorBasic.getBodySurface(60, 185, 22), 0.001);
        Assert.assertEquals(1.52777777, BodyDataCalculatorBasic.getBodySurface(55, 100, 10), 0.001);
    }

}