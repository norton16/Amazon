import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 *
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {

        ArrayList<Vehicle> loadedVehicles = new ArrayList<>();
        try {

            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                String[] line = br.readLine().split(",");
                if (line[0].equalsIgnoreCase("Drone")) {
                    Drone d = new Drone(line[1], Double.parseDouble(line[2]));
                    loadedVehicles.add(d);
                }
                if (line[0].equalsIgnoreCase("Truck")) {
                    Truck t = new Truck(line[1], Double.parseDouble(line[2]));
                    loadedVehicles.add(t);
                }
                if (line[0].equalsIgnoreCase("CargoPlane")) {
                    CargoPlane cg = new CargoPlane(line[1], Double.parseDouble(line[2]));
                    loadedVehicles.add(cg);
                }
            }

            br.close();
            fr.close();

            return loadedVehicles;
        } catch (Exception e) {
            return loadedVehicles;
        }


    }








    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        //TODO
        ArrayList<Package> packages = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                String[] word = br.readLine().split(",");

                packages.add(new Package(word[0], word[1],
                        Double.parseDouble(word[2]),
                        Double.parseDouble(word[3]),
                        new ShippingAddress(word[4], word[5], word[6], word[7], Integer.parseInt(word[8]))));

            }
            br.close();
            fr.close();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
        return packages;
    }






    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        //TODO
        Double thisProfit = 0.0;
        try {
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                Double word = Double.parseDouble(br.readLine());

                thisProfit += word;
            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            return 0;
        }
        return thisProfit;
    }





    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        //TODO
        int number = 0;
        try {
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                int word = Integer.parseInt(br.readLine());

                number += word;
            }
            br.close();
            fr.close();
        }
        catch (Exception e) {
            return 0;
        }

        return number;
    }




    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        //TODO
        String prime = "";
        try {
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                String word = br.readLine();

                prime += word;
            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            return false;
        }
        return prime.equals("1");
    }





    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        //TODO
        String vehicles2 = "";
        for (int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i) instanceof Truck)
            {
                vehicles2 += ("Truck," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
            }
            if(vehicles.get(i) instanceof Drone)
            {
                vehicles2 += ("Drone," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
            }
            if(vehicles.get(i) instanceof CargoPlane)
            {
                vehicles2 += ("Cargoplane," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
            }
            vehicles2 += '\n';
        }
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(vehicles2);

            bw.close();
            fw.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }

    }




    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        //TODO

        String packages2 = "";
        for (int i = 0; i < packages.size(); i++)
        {
            packages2 += (packages.get(i).getID() + ",");
            packages2 += (packages.get(i).getProduct() + ",");
            packages2 += (packages.get(i).getWeight() + ",");
            packages2 += (packages.get(i).getPrice() + ",");
            packages2 += (packages.get(i).getDestination().getBuyerName() + ",");
            packages2 += (packages.get(i).getDestination().getAddress() + ",");
            packages2 += (packages.get(i).getDestination().getCity() + ",");
            packages2 += (packages.get(i).getDestination().getState() + ",");
            packages2 += (packages.get(i).getDestination().getZipCode());
            packages2 += '\n';
        }
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(packages2);

            bw.close();
            fw.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }




    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        //TODO
        String profit2 = String.valueOf(profit);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(profit2);

            fw.close();
            bw.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }





    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        //TODO
        String profit2 = String.valueOf(nPackages);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(profit2);

            fw.close();
            bw.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }

    }






    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        //TODO
        String prime = "";
        if (primeDay)
        {
            prime = "1";
        }
        if (!primeDay)
        {
            prime = "0";
        }
        try {

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(prime);

            fw.close();
            bw.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}
