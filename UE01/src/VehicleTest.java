import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class VehicleTest {
    public static void testFileSize() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.csv"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            if (lines > 100) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("File longer than 100 lines");
        }
    }

    public static List<Vehicle> readVehicles(Path file) throws IOException {
        String line;
        List<Vehicle> vehicleList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(file)))) {
            while ((line = reader.readLine()) != null) {
                try {
                    int semicolonCount = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ';')
                            semicolonCount++;
                    }
                    if (semicolonCount == 0) {
                        vehicleList.add(new Bike(Integer.parseInt(line)));
                    } else if (semicolonCount == 1) {
                        String[] splitted = line.split(";");
                        vehicleList.add(new Car(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1])));
                    } else if (semicolonCount == 2) {
                        String[] splitted = line.split(";");
                        boolean tailLift = "1".equals(splitted[2]);  // Improved boolean parsing
                        vehicleList.add(new Truck(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), tailLift));
                    }
                }catch(Exception e){
                    System.out.println("file contains invalid line");
                }
            }
        }catch(Exception e){
            System.out.println("file not found");
        }

        return vehicleList;
    }

    public static int sumSeats(List<Vehicle> testVehicles) {
        int sumSeats = 0;
        for (int i = 0; i < testVehicles.size(); i++) {
           sumSeats += testVehicles.get(i).getSeats();
        }
        return sumSeats;
    }

    public static int sumCapacity(List<Vehicle> testVehicles) {
        int sumCapacity = 0;
        for (int i = 0; i < testVehicles.size(); i++) {
            sumCapacity += testVehicles.get(i).getCapacity();
        }
        return sumCapacity;
    }
    public static int countTailLifts(List<Vehicle> testVehicles) {
        int countTailLifts = 0;
        for (int i = 0; i < testVehicles.size(); i++) {
            countTailLifts += testVehicles.get(i).hasTailLift() ? 1 : 0;
        }
        return countTailLifts;
    }
}
