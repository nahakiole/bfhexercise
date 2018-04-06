package project9.tests;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Candidate {

    private String firstName;
    private String lastName;
    private String city;
    private Integer votes;
    private GregorianCalendar birthdate;
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Candidate(String firstName, String lastName, String city, Integer votes, GregorianCalendar birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.votes = votes;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        format.setCalendar(birthdate);
        return firstName + ' ' + lastName + ", " + city + ", " + votes + ", " + format.format(birthdate.getTime());
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
