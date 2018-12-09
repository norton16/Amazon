import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    private static double currentWeight;
    final double GAS_RATE = 2.33;

    /**
     * Default Constructor
     */
    //============================================================================
    //TODO

    public CargoPlane() {

    }

    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================

    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);

    }

    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */

    double cargoCurrentWeight = 0;

    public void fill(ArrayList<Package> warehousePackages) {

        cargoCurrentWeight = 0;
        int maxRange = 0;

        for (int i = 0; i < warehousePackages.size(); i++) {
            int thisRange = Math.abs(getZipDest() - warehousePackages.get(i).getDestination().getZipCode());
            if (thisRange > maxRange) {
                maxRange = thisRange * 10;
            }

        }

        for (int i = 0; i < warehousePackages.size(); i++) {
            for (int j = 0; j <= maxRange; j++) {

                if (Math.abs(warehousePackages.get(i).getDestination().getZipCode() - getZipDest()) == j) {
                    Package currentPackage = warehousePackages.get(i);
                    if (currentPackage.getWeight() + cargoCurrentWeight < getMaxWeight()) {
                        getPackages().add(currentPackage);
                        cargoCurrentWeight += currentPackage.getWeight();
                    }
                }
            }


        }

    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double profit = 0;
        for (int i = 0; i < getPackages().size(); i++) {
            Package current = getPackages().get(i);
            double price = current.getPrice();
            profit += price;

        }

        double gasPrice = GAS_RATE * (getMaxRange() * 10);
        profit -= gasPrice;
        return profit;

    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {

        String packageLabels = "";

        for (int i = 0; i < getPackages().size(); i++) {
            String thisPackage = getPackages().get(i).shippingLabel();
            packageLabels += thisPackage;

        }
        String report = "==========Cargo Plane Report==========\n" +
                "License Plate No.: " + getLicensePlate() + "\n" +
                "Destination: " + getZipDest() + "\n" +
                "Weight Load: " + cargoCurrentWeight + "/" + getMaxWeight() + "\n" +
                "Net Profit: $" + getProfit() + "\n" +
                "=====Shipping Labels=====\n" +
                "====================\n" + packageLabels;

        return report;

    }



}