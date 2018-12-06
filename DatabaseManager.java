import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

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
       //TODO
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            File f = new File(file.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String word = br.readLine();
                if (word == null) {
                    break;
                }
                if (word.startsWith("Drone"))
                {
                    String[] v = word.split(",");
                    vehicles.add(new Drone(v[2], Double.valueOf(v[3])));
                }
                if (word.startsWith("Truck"))
                {
                    String[] v = word.split(",");
                    vehicles.add(new Truck(v[2], Double.valueOf(v[3])));
                }
                if (word.startsWith("Cargo Plane"))
                {
                    String[] v = word.split(",");
                    vehicles.add(new Truck(v[2], Double.valueOf(v[3])));
                }


            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
        return vehicles;
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
            File f = new File(file.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String word = br.readLine();
                if (word == null) {
                    break;
                }
                    String[] v = word.split(",");
                    packages.add(new Package(v[0], v[1],
                            Double.valueOf(v[2]),
                            Double.valueOf(v[3]),
                            new ShippingAddress(v[4], v[5], v[6], v[7], Integer.valueOf(v[8]))));

            }
            fr.close();
            br.close();
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
        String profit = "";
        try {
            File f = new File(file.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String word = br.readLine();
                if (word == null) {
                    break;
                }
               profit += word;
            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            return 0;
        }
        return Double.valueOf(profit);
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
        String number = "";
        try {
            File f = new File(file.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String word = br.readLine();
                if (word == null) {
                    break;
                }
                number += word;
            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            return 0;
        }
        return Integer.valueOf(number);

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
            File f = new File(file.getPath());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String word = br.readLine();
                if (word == null) {
                    break;
                }
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
            File f = new File(file.getPath());
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < vehicles.size(); i++)
            {
                bw.write(vehicles2);
            }

            fw.close();
            bw.close();
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
            packages2 += (packages.get(i).getDestination().getZip());
            packages2 += '\n';
        }
        try {
            File f = new File(file.getPath());
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < packages.size(); i++)
            {
                bw.write(packages2);
            }

            fw.close();
            bw.close();
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
            File f = new File(file.getPath());
            FileWriter fw = new FileWriter(f);
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
            File f = new File(file.getPath());
            FileWriter fw = new FileWriter(f);
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
            prime = "2";
        }
        try {
            File f = new File(file.getPath());
            FileWriter fw = new FileWriter(f);
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