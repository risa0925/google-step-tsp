import java.io.*;
import java.util.*;

class TravellingSalesmanProblem {

    static ArrayList<Double> position_x = new ArrayList<Double>();
    static ArrayList<Double> position_y = new ArrayList<Double>();
    static ArrayList<Integer> order = new ArrayList<Integer>();
    static int startCity = 0;
    static ArrayList<Integer> distance = new ArrayList<Integer>();


    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage : Please specify file names for inputting and outputting.");
            System.exit(1);
        }
        inputFile(args[0]);
        getStartCity();
        calcBestRoute();
        outputFile(args[1]);
        runTest();

    }

    public static void inputFile(String inputFile) {

        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();//do not read the first line
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",", 0);
                position_x.add(Double.parseDouble(lineData[0]));
                position_y.add(Double.parseDouble(lineData[1]));
                count++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getStartCity() {
        for (int cityNum = 0; cityNum < position_x.size(); cityNum++) {
            double x = position_x.get(cityNum);
            double startPosition_x = position_x.get(startCity);
            if (startPosition_x > x) {
                startCity = cityNum;
            } else if (startPosition_x == x) {
                double y = position_y.get(cityNum);
                double startPosition_y = position_y.get(startCity);
                if (startPosition_y > y) {
                    startCity = cityNum;
                }
            }
        }
    }


    public static void calcBestRoute() {
        calcDistance();
        for (int i = 0; i < distance.size(); i++) {
            int min = distance.indexOf(Collections.min(distance));
            order.add(min);
            distance.set(min, Integer.MAX_VALUE);
        }
    }


    public static void calcDistance() {
        for (int nextCity = 0; nextCity < position_x.size(); nextCity++) {
            if (nextCity == startCity) {
                distance.add(0);
            } else {
                double distance_x = Math.pow((position_x.get(nextCity) - position_x.get(startCity)), 2);
                double distance_y = Math.pow((position_y.get(nextCity) - position_y.get(startCity)), 2);
                distance.add((int) Math.sqrt(distance_x + distance_y));
            }
        }
    }

    public static void outputFile(String outputFile) {
        try {
            FileWriter fw = new FileWriter(outputFile, false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            pw.println("index");
            for (int cityNum = 0; cityNum < order.size(); cityNum++) {
                pw.println(order.get(cityNum));
            }
            pw.close();
            System.out.println("Complete to overwrite!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runTest() {

    }
}