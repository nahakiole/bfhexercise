package ch.robinglauser.bfhexercise.medhelp;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Assembly of methods to calculate body surface, body mass index, ideal weight, normal weight, birth date and age.
 */
public class MedicineCalculator {


    public enum Gender {
        MAN, WOMAN, OTHER
    }

    /**
     * Get the body surface from the weight, height and age of a person.
     *
     * @param weight in kg
     * @param height in cm
     * @param age    in years
     * @return bodysurface in m^2
     */
    public static double getBodySurface(double weight, double height, int age) {
        if (age > 17) {
            if (age < 22) {
                return (getBodySurfaceAdult(weight, height) + getBodySurfaceChild(weight, height)) / 2;
            }
            return getBodySurfaceAdult(weight, height);
        }
        return getBodySurfaceChild(weight, height);
    }

    /**
     * Get body surface for an adult (from age 22)
     *
     * @param weight in kg
     * @param height in cm
     * @return bodysurface in m^2
     */
    private static double getBodySurfaceAdult(double weight, double height) {
        return (Math.pow(weight, 0.425) * Math.pow(height, 0.725) * 71.84) / 10000;
    }

    /**
     * Get body surface for a child (age 0-16)
     *
     * @param weight in kg
     * @param height in cm
     * @return bodysurface in m^2
     */
    private static double getBodySurfaceChild(double weight, double height) {
        return (weight * height) / 3600;
    }

    /**
     * Get the body mass index from the height and weight of a person.
     *
     * @param weight in kg
     * @param height in cm
     * @return
     */
    public static double getBodyMassIndex(double weight, double height) {
        return weight / Math.pow(height / 100, 2);
    }

    /**
     * Get the ideal weight in kg from the height and gender
     *
     * @param height in cm
     * @param gender Gender as an enum
     * @return ideal weight
     */
    public static double getIdealWeight(double height, Gender gender) {
        if (gender == Gender.MAN) {
            return (height - 100) * 0.9;
        }
        return (height - 100) * 0.8;
    }

    /**
     * Get the normal weight in kg from the height.
     *
     * @param height in cm
     * @return normal weight
     */
    public static double getNormalWeight(double height) {
        return (height - 100);

    }

    /**
     * Get birthdate from last period.
     *
     * @param lastPeriod Date of last period
     * @return birthdate
     */
    public static DateTime getBirthDate(DateTime lastPeriod) {
        return lastPeriod.plusWeeks(40);
    }

    /**
     * Get birthdate from last period with the Naegele Method
     *
     * @param lastPeriod Date of last period
     * @return birthdate
     */
    public static DateTime getBirthDateWithNaegeleMethod(DateTime lastPeriod) {
        DateTime birthday = new DateTime(lastPeriod);
        return birthday.plusDays(7).minusMonths(3).plusYears(1);
    }

    /**
     * Get birthdate from last period with the Naegele Method
     *
     * @param lastPeriod Date of last period
     * @return birthdate
     */
    public static GregorianCalendar getBirthDateWithNaegeleMethod(GregorianCalendar lastPeriod) {
        GregorianCalendar birthdate = (GregorianCalendar) lastPeriod.clone();
        birthdate.add(Calendar.DAY_OF_YEAR, 7);
        birthdate.add(Calendar.MONTH, -3);
        birthdate.add(Calendar.YEAR, 1);
        return birthdate;
    }

    /**
     * Get birthdate from last period.
     *
     * @param birthday Date of birth
     * @return birthdate
     */
    public static int getAge(DateTime birthday) {
        if (DateTime.now().getMonthOfYear() < birthday.getMonthOfYear()) {
            return DateTime.now().getYear() - birthday.getYear() - 1;
        }
        if (DateTime.now().getMonthOfYear() == birthday.getMonthOfYear()) {
            if (DateTime.now().getDayOfMonth() < birthday.getDayOfMonth()) {
                return DateTime.now().getYear() - birthday.getYear() - 1;
            }
        }
        return DateTime.now().getYear() - birthday.getYear();
    }

    /**
     * Calculate flow rate with Cockroft und Gault
     *
     * @param age in years
     * @param weight in kg
     * @param serumkreatinin in mg/dl
     * @return flow rate of filtered fluid through the kidney.
     */
    public static double getRenalFunction(int age, double weight, double serumkreatinin, Gender gender){
        System.out.println(gender);
        return ((140 - age) / serumkreatinin) * (weight / 72) * (gender == Gender.MAN ? 1 : 0.85);
    }


}
