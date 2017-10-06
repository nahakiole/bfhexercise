package ch.robinglauser.bfhexercise.exercises;

/**
 * The class describes a mailing address with street, street number, postal code (zip), and city.
 * Methods for getting and setting components of the address are provided.
 * The input data are not validated.
 *
 * @author Robin Glauser
 * @version V03.10.2017
 */

public class Address {

    private String street = "";
    private String streetNumber = "";
    private String postalCode = "";
    private String city = "";

    /**
     * Constructs a complete mailing address.
     *
     * @param street       The street of the Address.
     * @param streetNumber The street number of the Address.
     * @param postalCode   The postal code of the Address.
     * @param city         The city of the Address.
     */
    public Address(String street, String streetNumber, String postalCode, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    /**
     * @return Returns a string representation of this Address.
     */
    public String toString() {
        return street + " " + streetNumber + ", " + postalCode + " " + city;
    }


    /**
     * Returns the street of this Address.
     *
     * @return the street of this Address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of this Address.
     *
     * @param street Street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the street number of this Address.
     *
     * @return the street number of this Address.
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the street number of this Address.
     *
     * @param streetNumber Street number
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Returns the postal code of this Address.
     *
     * @return the postal code of this Address.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of this Address.
     *
     * @param postalCode Postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the city of this Address.
     *
     * @return the city of this Address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of this Address.
     *
     * @param city City
     */
    public void setCity(String city) {
        this.city = city;
    }
}
