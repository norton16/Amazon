/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {


    private  String name;
    private  String address;
    private  String city;
    private  String state;
    private  int ZipCode;

    public ShippingAddress() {

    }

    public ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.ZipCode = zipCode;
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
        return ZipCode;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }

    public  String toString(ShippingAddress currentBuyer) {
        return "TO: " + name + "\n" +
                address + "\n" +
                city + ", " + state +
                ", " + ZipCode;

    }
}
