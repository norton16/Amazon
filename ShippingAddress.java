/**
 * Project 05 -- Amazon Warehouse
 *
 * This program uses classes and interfaces to simulate Amazon.
 *
 * @author Brian Norton, Briana Crowe, lab sec 015
 *
 * @version December 9, 2018
 *
 */

/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {


    private static String name;
    private static String address;
    private static String city;
    private static String state;
    private static int zipCode;

    public ShippingAddress() {

    }

    public ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public static String toString(ShippingAddress currentBuyer) {
        String label = String.format("====================\n" +
                        "TO: %s" + "\n" +
                        "%s           \n" +
                        "%s, %s, %d\n", name, address, city,
                state, zipCode);
        return label;
    }
}
