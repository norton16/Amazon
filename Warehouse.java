import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
    final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;
    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages)
    {
        System.out.println("==========Statistics==========\n" +
                "Profits:                 $" + profits + "\n" +
                "Packages Shipped:                " + packagesShipped + "\n" +
                "Packages in Warehouse:           " + numberOfPackages + "\n" +
                "==============================\n");
    }

    public static int zipMode(ArrayList<ShippingAddress> sa) {
        ArrayList<Integer> zipDests = new ArrayList<>();
        for (int i = 0; i < sa.size(); i++) {
            zipDests.add(sa.get(i).getZipCode());
        }
        HashMap<Integer, Integer> modeHash = new HashMap<Integer, Integer>();
        int maxZip = 1;
        int mode = 0;

        for (int i = 0; i < zipDests.size(); i++) {
            if (modeHash.get(zipDests.get(i)) != null) {
                int freq = modeHash.get(zipDests.get(i));
                freq += 1;
                modeHash.put(zipDests.get(i), freq);

                if (freq > maxZip) {
                    maxZip = freq;
                    mode = zipDests.get(i);

                } else {
                    modeHash.put(zipDests.get(i), 1);
                }

                return mode;

            }

        }
        return mode;

    }
    /**
     * Main Method
     *
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
        //TODO

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        DatabaseManager.loadVehicles(VEHICLE_FILE);
        DatabaseManager.loadPackages(PACKAGE_FILE);
        DatabaseManager.loadProfit(PROFIT_FILE);
        DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);


        //2) Show menu and handle user inputs
        Scanner scan = new Scanner(System.in);
        double profit = 0;
        int numPackagesShipped = 0;

        if(DatabaseManager.loadPrimeDay(PRIME_DAY_FILE))
        {
            System.out.println("==========Options==========\n" +
                    "1) Add Package\n" +
                    "2) Add Vehicle\n" +
                    "3) Deactivate Prime Day\n" +
                    "4) Send Vehicle\n" +
                    "5) Print Statistics\n" +
                    "6) Exit\n" +
                    "===========================");
        } else {
            System.out.println("==========Options==========\n" +
                    "1) Add Package\n" +
                    "2) Add Vehicle\n" +
                    "3) Activate Prime Day\n" +
                    "4) Send Vehicle\n" +
                    "5) Print Statistics\n" +
                    "6) Exit\n" +
                    "===========================");
        }
        String choice = scan.nextLine();
        if(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")) {
            do {
                System.out.println("Error: Option not available.");
                choice = scan.nextLine();
            }
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6"));
        }

        if (choice.equals("1"))//////////////
        {
            System.out.println("Enter Package ID:");
            String id = scan.nextLine();
            System.out.println("Enter Product Name:");
            String productname = scan.nextLine();
            System.out.println("Enter Weight:");
            Double weight = Double.valueOf(scan.nextLine());
            System.out.println("Enter Price:");
            Double price =  Double.valueOf(scan.nextLine());
            System.out.println("Enter Buyer Name:");
            String buyername = scan.nextLine();
            System.out.println("Enter Address:");
            String address = scan.nextLine();
            System.out.println("Enter City:");
            String city = scan.nextLine();
            System.out.println("Enter State:");
            String state = scan.nextLine();
            System.out.println("Enter Zip Code:");
            int zip = scan.nextInt();

            if(DatabaseManager.loadPrimeDay(PRIME_DAY_FILE))
            {
                price = price * 0.85;
            }

            Package p = new Package(id, productname, weight, price,
                    new ShippingAddress(buyername, address, city, state, zip));
            System.out.println(p.shippingLabel());

            ArrayList<Package> x = DatabaseManager.loadPackages(PACKAGE_FILE);
            x.add(p);
            DatabaseManager.savePackages(PACKAGE_FILE, x);
        }

        else if (choice.equals("2")) ////////////
        {
            Vehicle vehicle = new Vehicle();
            System.out.println("Vehicle Options:" + '\n' + "1) Truck" + '\n' + "2) Drone" + '\n' + "3) Cargo Plane");
            choice = scan.nextLine();
            if(!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                do {
                    System.out.println("Error: Option not available.");
                    choice = scan.nextLine();
                }
                while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
            }
            else if (choice.equals("1"))
            {
                System.out.println("Enter License Plate No.:");
                String licensePlate = scan.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                int maxWeight = Integer.valueOf(scan.nextLine());
                vehicle = new Truck(licensePlate, maxWeight);
            }
            else if (choice.equals("2"))
            {
                System.out.println("Enter License Plate No.:");
                String licensePlate = scan.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                int maxWeight = Integer.valueOf(scan.nextLine());
                vehicle = new Drone(licensePlate, maxWeight);
            }
            else if (choice.equals("3"))
            {
                System.out.println("Enter License Plate No.:");
                String licensePlate = scan.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                int maxWeight = Integer.valueOf(scan.nextLine());
                vehicle = new CargoPlane(licensePlate, maxWeight);
            }
            ArrayList<Vehicle> x = DatabaseManager.loadVehicles(VEHICLE_FILE);
            x.add(vehicle);
            DatabaseManager.saveVehicles(VEHICLE_FILE, x);
        }

        else if (choice.equals("3"))/////////////////////////////////////
        {
            if(DatabaseManager.loadPrimeDay(PRIME_DAY_FILE))
            {
                for (int i = 0; i < DatabaseManager.loadPackages(PACKAGE_FILE).size(); i++)
                {
                    double noPrimePrice = DatabaseManager.loadPackages(PACKAGE_FILE).get(i).getPrice() * 1.15;
                    DatabaseManager.loadPackages(PACKAGE_FILE).get(i).setPrice(noPrimePrice);
                }
                DatabaseManager.savePrimeDay(PRIME_DAY_FILE, false);
            }
            else if (!DatabaseManager.loadPrimeDay(PRIME_DAY_FILE))
            {
                for (int i = 0; i < DatabaseManager.loadPackages(PACKAGE_FILE).size(); i++)
                {
                    double PrimePrice = DatabaseManager.loadPackages(PACKAGE_FILE).get(i).getPrice() * 0.85;
                    DatabaseManager.loadPackages(PACKAGE_FILE).get(i).setPrice(PrimePrice);
                }
                DatabaseManager.savePrimeDay(PRIME_DAY_FILE, true);
            }
        }

        else if (choice.equals("4")) {
            ArrayList<Vehicle> availableVehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
            if (availableVehicles.size() == 0) {
                System.out.println("Error: No vehicles available.");

            } else {
                System.out.println("Options:\n" +
                        "1) Send Truck\n" +
                        "2) Send Drone\n" +
                        "3) Send Cargo Plane\n" +
                        "4) Send First Available");

                String carType = scan.nextLine();
                try {
                    int numCarType = Integer.parseInt(carType);
                    switch (numCarType) {
                        case 1:
                            for (int i = 0; i < availableVehicles.size(); i++) {
                                if (availableVehicles.get(i) instanceof Truck) {
                                    Truck truck = (Truck) availableVehicles.get(i);
                                    System.out.println("ZIP Code Options:\n" +
                                            "1) Send to first ZIP Code\n" +
                                            "2) Send to mode of ZIP Codes");
                                    String zipCode = scan.nextLine();
                                    try {
                                        int numZipCode = Integer.parseInt(zipCode);
                                        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
                                        switch (numZipCode) {
                                            case 1:
                                                ShippingAddress packDest = packages.get(0).getDestination();
                                                int zipDest = packDest.getZipCode();
                                                truck.setZipDest(zipDest);
                                                truck.fill(packages);
                                                profit += truck.getProfit();
                                                truck.report();
                                                numPackagesShipped += truck.getPackages().size();

                                                break;

                                            case 2:
                                                ShippingAddress dest = packages.get(0).getDestination();
                                                ArrayList<ShippingAddress> dests = new ArrayList<>();
                                                for (int j = 0; j < packages.size(); j++) {
                                                    dests.add(packages.get(i).getDestination());
                                                }
                                                for (int j = dests.size() - 1; j > 0; j--) {
                                                    if (dests.get(j).getZipCode() != dests.get(j - 1).getZipCode()) {
                                                        truck.setZipDest(dests.get(i).getZipCode());
                                                    } else {
                                                        truck.setZipDest(zipMode(dests));

                                                    }


                                                }
                                                truck.fill(packages);
                                                profit += truck.getProfit();
                                                numPackagesShipped += truck.getPackages().size();
                                                truck.report();
                                                break;
                                            default:
                                                System.out.println("Error: Option not available.");
                                                break;

                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("Error: Option not available.");
                                    }

                                } else {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    break;
                                }

                            }

                        case 2:
                            for (int i = 0; i < availableVehicles.size(); i++) {
                                if (availableVehicles.get(i) instanceof Drone) {
                                    Drone drone = (Drone) availableVehicles.get(i);
                                    System.out.println("ZIP Code Options:\n" +
                                            "1) Send to first ZIP Code\n" +
                                            "2) Send to mode of ZIP Codes");
                                    String zipCode = scan.nextLine();
                                    try {
                                        int numZipCode = Integer.parseInt(zipCode);
                                        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
                                        switch (numZipCode) {
                                            case 1:
                                                ShippingAddress packDest = packages.get(0).getDestination();
                                                int zipDest = packDest.getZipCode();
                                                drone.setZipDest(zipDest);
                                                drone.fill(packages);
                                                profit += drone.getProfit();
                                                numPackagesShipped += drone.getPackages().size();
                                                drone.report();
                                                break;

                                            case 2:
                                                ShippingAddress dest = packages.get(0).getDestination();
                                                ArrayList<ShippingAddress> dests = new ArrayList<>();
                                                for (int j = 0; j < packages.size(); j++) {
                                                    dests.add(packages.get(i).getDestination());
                                                }
                                                for (int j = dests.size() - 1; j > 0; j--) {
                                                    if (dests.get(j).getZipCode() != dests.get(j - 1).getZipCode()) {
                                                        drone.setZipDest(dests.get(i).getZipCode());
                                                    } else {
                                                        drone.setZipDest(zipMode(dests));

                                                    }


                                                }
                                                drone.fill(packages);
                                                profit += drone.getProfit();
                                                numPackagesShipped += drone.getPackages().size();
                                                drone.report();
                                                break;
                                            default:
                                                System.out.println("Error: Option not available.");
                                                break;

                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("Error: Option not available.");
                                    }

                                } else {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    break;
                                }

                            }

                        case 3:
                            for (int i = 0; i < availableVehicles.size(); i++) {
                                if (availableVehicles.get(i) instanceof CargoPlane) {
                                    CargoPlane cg = (CargoPlane) availableVehicles.get(i);
                                    System.out.println("ZIP Code Options:\n" +
                                            "1) Send to first ZIP Code\n" +
                                            "2) Send to mode of ZIP Codes");
                                    String zipCode = scan.nextLine();
                                    try {
                                        int numZipCode = Integer.parseInt(zipCode);
                                        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
                                        switch (numZipCode) {
                                            case 1:
                                                ShippingAddress packDest = packages.get(0).getDestination();
                                                int zipDest = packDest.getZipCode();
                                                cg.setZipDest(zipDest);
                                                cg.fill(packages);
                                                profit += cg.getProfit();
                                                numPackagesShipped += cg.getPackages().size();
                                                cg.report();
                                                break;

                                            case 2:
                                                ShippingAddress dest = packages.get(0).getDestination();
                                                ArrayList<ShippingAddress> dests = new ArrayList<>();
                                                for (int j = 0; j < packages.size(); j++) {
                                                    dests.add(packages.get(i).getDestination());
                                                }
                                                for (int j = dests.size() - 1; j > 0; j--) {
                                                    if (dests.get(j).getZipCode() != dests.get(j - 1).getZipCode()) {
                                                        cg.setZipDest(dests.get(i).getZipCode());
                                                    } else {
                                                        cg.setZipDest(zipMode(dests));

                                                    }


                                                }
                                                cg.fill(packages);
                                                profit += cg.getProfit();
                                                numPackagesShipped += cg.getPackages().size();
                                                cg.report();
                                                break;
                                            default:
                                                System.out.println("Error: Option not available.");
                                                break;

                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("Error: Option not available.");
                                    }

                                } else {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    break;
                                }

                            }

                        case 4:
                            Vehicle vehicle = availableVehicles.get(0);
                            if (vehicle instanceof Truck) {
                                vehicle = (Truck) vehicle;
                            } else if (vehicle instanceof Drone) {
                                vehicle = (Drone) vehicle;
                            } else if (vehicle instanceof CargoPlane) {
                                vehicle = (CargoPlane) vehicle;
                            }

                            System.out.println("ZIP Code Options:\n" +
                                    "1) Send to first ZIP Code\n" +
                                    "2) Send to mode of ZIP Codes");
                            String zipCode = scan.nextLine();
                            try {
                                int numZipCode = Integer.parseInt(zipCode);
                                ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
                                switch (numZipCode) {
                                    case 1:
                                        ShippingAddress packDest = packages.get(0).getDestination();
                                        int zipDest = packDest.getZipCode();
                                        vehicle.setZipDest(zipDest);
                                        vehicle.fill(packages);
                                        profit += vehicle.getProfit();
                                        numPackagesShipped += vehicle.getPackages().size();
                                        vehicle.report();
                                        break;

                                    case 2:
                                        ShippingAddress dest = packages.get(0).getDestination();
                                        ArrayList<ShippingAddress> dests = new ArrayList<>();
                                        for (int j = 0; j < packages.size(); j++) {
                                            dests.add(packages.get(j).getDestination());
                                        }
                                        for (int j = dests.size() - 1; j > 0; j--) {
                                            if (dests.get(j).getZipCode() != dests.get(j - 1).getZipCode()) {
                                                vehicle.setZipDest(dests.get(j).getZipCode());
                                            } else {
                                                vehicle.setZipDest(zipMode(dests));

                                            }


                                        }
                                        vehicle.fill(packages);
                                        profit += vehicle.getProfit();
                                        numPackagesShipped += vehicle.getPackages().size();
                                        vehicle.report();
                                        break;
                                    default:
                                        System.out.println("Error: Option not available.");
                                        break;

                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Error: Option not available.");
                            }


                        default:
                            System.out.println("Error: Option not available.");
                            break;

                    }


                } catch (NumberFormatException e) {
                    System.out.println("Error: Option not available.");
                }

            }
        }

        else if (choice.equals("5")) {
            ArrayList<Package> inWarehouse = DatabaseManager.loadPackages(PACKAGE_FILE);
            int sizeInWarehouse = inWarehouse.size();
            int left = sizeInWarehouse - numPackagesShipped;
            printStatisticsReport(profit, numPackagesShipped, left);
        }
        else if (choice.equals("6")) {

            return;


        }
        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager

    }
}






    	
    

