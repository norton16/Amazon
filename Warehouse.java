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



    public static int zipMode(ArrayList<ShippingAddress> sa) {
        ArrayList<Integer> zipDests = new ArrayList<>();
        for (int i = 0; i < sa.size(); i++) {
            zipDests.add(sa.get(i).getZip());
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

        Scanner scan = new Scanner(System.in);
    	String choice = scan.nextLine();
    	double profit = 0;
    	int numPackagesShipped = 0;
    	try {
            int choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        ArrayList<Vehicle> availableVehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
                        if (availableVehicles.size() == 0) {
                            System.out.println("Error: No vehicles available.");
                            break;
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
                                                            int zipDest = packDest.getZip();
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
                                                                if (dests.get(j).getZip() != dests.get(j - 1).getZip()) {
                                                                    truck.setZipDest(dests.get(i).getZip());
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
                                                            int zipDest = packDest.getZip();
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
                                                                if (dests.get(j).getZip() != dests.get(j - 1).getZip()) {
                                                                    drone.setZipDest(dests.get(i).getZip());
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
                                                            int zipDest = packDest.getZip();
                                                            cg.setZipDest(zipDest);
                                                            cg.fill(packages);
                                                            profit += cg.getProfit();
                                                            numPackagesShipped += cg.getPackages().size();                                                            cg.report();
                                                            break;

                                                        case 2:
                                                            ShippingAddress dest = packages.get(0).getDestination();
                                                            ArrayList<ShippingAddress> dests = new ArrayList<>();
                                                            for (int j = 0; j < packages.size(); j++) {
                                                                dests.add(packages.get(i).getDestination());
                                                            }
                                                            for (int j = dests.size() - 1; j > 0; j--) {
                                                                if (dests.get(j).getZip() != dests.get(j - 1).getZip()) {
                                                                    cg.setZipDest(dests.get(i).getZip());
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
                                                    int zipDest = packDest.getZip();
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
                                                        if (dests.get(j).getZip() != dests.get(j - 1).getZip()) {
                                                            vehicle.setZipDest(dests.get(j).getZip());
                                                        } else {
                                                            vehicle.setZipDest(zipMode(dests));

                                                        }


                                                    }
                                                    vehicle.fill(packages);
                                                    profit += vehicle.getProfit();
                                                    numPackagesShipped += vehicle.getPackages().size();                                                    vehicle.report();
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

                    case 5:
                        ArrayList<Package> inWarehouse = DatabaseManager.loadPackages(PACKAGE_FILE);
                        int sizeInWarehouse = inWarehouse.size();
                        int left = sizeInWarehouse - numPackagesShipped;
                        System.out.println("==========Statistics==========\n" +
                                "Profits:                 $" + profit + "\n" +
                                "Packages Shipped:                " + numPackagesShipped + "\n" +
                                "Packages in Warehouse:           " + left +"\n" +
                                "==============================\n");

                    case 6:

                        return;

                    default:
                        System.out.println("Error: Option not available.");
                        break;


                }
        } catch (NumberFormatException e) {
            System.out.println("Error: Option not available.");

        }


    	
    	
    	//2) Show menu and handle user inputs
    	
    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}
