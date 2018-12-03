/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {


    private static String buyerName;
    private static String address;
    private static String city;
    private static String state;
    private static int zip;

    public ShippingAddress() {

    }

    public ShippingAddress(String buyerName, String address, String city, String state, int zip) {
        this.buyerName = buyerName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public static String toString(ShippingAddress currentBuyer) {
        return "TO: " + buyerName + "\n" +
                address + "\n" +
                city + ", " + state +
                ", " + zip;

    }
}
