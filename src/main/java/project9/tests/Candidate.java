package project9.tests;

public class Candidate {

    private String firstName;
    private String lastName;
    private String city;
    private Integer votes;

    public Candidate(String firstName, String lastName, String city, Integer votes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.votes = votes;
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
        return firstName + ' ' + lastName + "," + city + "," + votes;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
