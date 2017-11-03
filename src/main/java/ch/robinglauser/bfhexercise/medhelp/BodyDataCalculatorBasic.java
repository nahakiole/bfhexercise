package ch.robinglauser.bfhexercise.medhelp;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Assembly of methods to calculate body surface, body mass index, ideal weight, normal weight, birth date and age.
 */
public class BodyDataCalculatorBasic {

    /**
     * Enum to differentiate gender.
     */
    public enum Gender {
        MAN, WOMAN, OTHER
    }

    /**
     * Enum for different BloodPressureCategories
     */
    public enum BloodPressureCategory {
        Hypotension,
        Desired,
        Prehypertension,
        Stage1Hypertension,
        Stage2Hypertension,
        HypertensiveUrgency,
        IsolatedSystolicHypertension
    }

    public enum BodyMassIndexCategory {
        VerySeverelyUnderweight,
        SeverelyUnderweight,
        Underweight,
        Normal,
        Overweight,
        ObeseClassI,
        ObeseClassII,
        ObeseClassIII
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
                return (getBSAadults(weight, height) + getBSAchildren(weight, height)) / 2;
            }
            return getBSAadults(weight, height);
        }
        return getBSAchildren(weight, height);
    }

    /**
     * Get body surface for an adult (from age 22)
     *
     * @param weight in kg
     * @param height in cm
     * @return bodysurface in m^2
     */
    private static double getBSAadults(double weight, double height) {
        return (Math.pow(weight, 0.425) * Math.pow(height, 0.725) * 71.84) / 10000;
    }

    /**
     * Get body surface for a child (age 0-16)
     *
     * @param weight in kg
     * @param height in cm
     * @return bodysurface in m^2
     */
    private static double getBSAyoungAdults(double weight, double height) {
        return (getBSAadults(weight, height) + getBSAchildren(weight, height)) / 2;
    }

    /**
     * Get body surface for a young adult (age 17-22)
     *
     * @param weight in kg
     * @param height in cm
     * @return bodysurface in m^2
     */
    private static double getBSAchildren(double weight, double height) {
        return (weight * height) / 3600;
    }

    /**
     * Get the body mass index from the height and weight of a person.
     *
     * @param weight in kg
     * @param height in cm
     * @return body mass index
     */
    public static double getBMI(double weight, double height) {
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
     * Get the ideal weight for men based on height
     *
     * @param height in cm
     * @return ideal weight for men based on height
     */
    public static double getIBWmen(double height) {
        return getIdealWeight(height, Gender.MAN);
    }


    /**
     * Get the ideal weight for women based on height
     *
     * @param height in cm
     * @return ideal weight for women based on height
     */
    public static double getIBWwomen(double height) {
        return getIdealWeight(height, Gender.WOMAN);
    }

    /**
     * Get the normal weight in kg from the height.
     *
     * @param height in cm
     * @return normal weight
     */
    public static double getNBW(double height) {
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
     * @param age            in years
     * @param weight         in kg
     * @param serumkreatinin in mg/dl
     * @return flow rate of filtered fluid through the kidney.
     */
    public static double getRenalFunction(int age, double weight, double serumkreatinin, Gender gender) {
        return ((140 - age) / serumkreatinin) * (weight / 72) * (gender == Gender.MAN ? 1 : 0.85);
    }

    /**
     * @param systolic
     * @param diastolic
     * @return
     */
    public static BloodPressureCategory getBloodPressureCategory(int systolic, int diastolic) {
        if (systolic < 90 && diastolic < 60) {
            return BloodPressureCategory.Hypotension;
        }
        if (systolic < 120 && diastolic < 80) {
            return BloodPressureCategory.Desired;
        }
        if (systolic < 139 && diastolic < 90) {
            return BloodPressureCategory.Prehypertension;
        }
        if (systolic < 159 && diastolic < 100) {
            return BloodPressureCategory.Stage1Hypertension;
        }
        if (systolic < 179 && diastolic < 110) {
            return BloodPressureCategory.Stage2Hypertension;
        }
        if (systolic >= 180 && diastolic >= 110) {
            return BloodPressureCategory.HypertensiveUrgency;
        }
        if (systolic >= 160 && diastolic < 90) {
            return BloodPressureCategory.IsolatedSystolicHypertension;
        }
        throw new IllegalArgumentException("This doesn't make sense.");
    }

    /**
     * Get the bodymass index category
     *
     * @param bodyMassIndex BMI as a double
     * @return bodymass index Category
     */
    public static BodyMassIndexCategory getBodyMassIndexCategory(double bodyMassIndex) {
        if (bodyMassIndex < 15) {
            return BodyMassIndexCategory.VerySeverelyUnderweight;
        }
        if (bodyMassIndex < 16) {
            return BodyMassIndexCategory.SeverelyUnderweight;
        }
        if (bodyMassIndex < 18.5) {
            return BodyMassIndexCategory.Underweight;
        }
        if (bodyMassIndex < 25) {
            return BodyMassIndexCategory.Normal;
        }
        if (bodyMassIndex < 30) {
            return BodyMassIndexCategory.Overweight;
        }
        if (bodyMassIndex < 35) {
            return BodyMassIndexCategory.ObeseClassI;
        }
        if (bodyMassIndex < 40) {
            return BodyMassIndexCategory.ObeseClassII;
        }
        return BodyMassIndexCategory.ObeseClassIII;
    }
}
