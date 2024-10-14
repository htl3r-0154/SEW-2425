
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class VehicleJUnitTest {
    private List<Vehicle> testVehicles;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        testVehicles = Arrays.asList(
                new Bike(1),
                new Car(5, 500),
                new Truck(2, 10000, true),
                new Bike(2),
                new Car(7, 700),
                new Truck(3, 20000, false)
        );
    }

    @Test
    void testBikeCreation() {
        Bike bike = new Bike(2);
        assertEquals(2, bike.getSeats());
        assertEquals(0, bike.getCapacity());
    }

    void testCarCreation() {
        Car car = new Car(5, 500);
        assertEquals(5, car.getSeats());
        assertEquals(500, car.getCapacity());
    }

    @Test
    void testTruckCreation() {
        Truck truck = new Truck(3, 15000, true);
        assertEquals(3, truck.getSeats());
        assertEquals(15000, truck.getCapacity());
        assertTrue(truck.hasTailLift());
    }

    @Test
    void testBikeToString() {
        Bike bike = new Bike(1);
        assertEquals("Bike: seats: 1", bike.toString());
    }

    @Test
    void testCarToString() {
        Car car = new Car(5, 500);
        assertEquals("Car: seats: 5, capacity: 500 kg", car.toString());
    }

    @Test
    void testTruckToString() {
        Truck truck = new Truck(2, 10000, true);
        assertEquals("Truck: seats: 2, capacity: 10000 kg, has tail lift", truck.toString());
    }


    @Test
    void testSumSeats() {
        assertEquals(20, VehicleTest.sumSeats(testVehicles));
    }

    @Test
    void testSumCapacity() {
        assertEquals(31200, VehicleTest.sumCapacity(testVehicles));
    }

    @Test
    void testCountTailLifts() {
        assertEquals(1, VehicleTest.countTailLifts(testVehicles));
    }

    @Test
    void testReadVehiclesValidFile() throws IOException {
        Path testFile = tempDir.resolve("test_vehicles.csv");
        Files.writeString(testFile, "1\n5;500\n2;10000;1\n");
        List<Vehicle> vehicles = VehicleTest.readVehicles(testFile);
        assertEquals(3, vehicles.size());
        assertTrue(vehicles.get(0) instanceof Bike);
        assertTrue(vehicles.get(1) instanceof Car);
        assertTrue(vehicles.get(2) instanceof Truck);
    }

    @Test
    void testReadVehiclesInvalidLine() throws IOException {
        Path testFile = tempDir.resolve("test_invalid.csv");
        Files.writeString(testFile, "1\n5;500\ninvalid\n2;10000;1\n");
        List<Vehicle> vehicles = VehicleTest.readVehicles(testFile);
        System.out.println(vehicles);
        assertEquals(3, vehicles.size());
    }

    @Test
    void testReadVehiclesEmptyFile() throws IOException {
        Path testFile = tempDir.resolve("test_empty.csv");
        Files.writeString(testFile, "");
        List<Vehicle> vehicles = VehicleTest.readVehicles(testFile);
        assertTrue(vehicles.isEmpty());
    }


    @Test
    void testSumSeatsEmptyList() {
        assertEquals(0, VehicleTest.sumSeats(List.of()));
    }

    @Test
    void testSumCapacityEmptyList() {
        assertEquals(0, VehicleTest.sumCapacity(List.of()));
    }

    @Test
    void testCountTailLiftsEmptyList() {
        assertEquals(0, VehicleTest.countTailLifts(List.of()));
    }

    @Test
    void testReadVehiclesNonexistentFile() throws IOException {
        Path nonexistentFile = tempDir.resolve("nonexistent.csv");
        List<Vehicle> vehicles = VehicleTest.readVehicles(nonexistentFile);
        assertTrue(vehicles.isEmpty());
    }
    @Test
    void testVehiclePolymorphism() {
        List<Vehicle> vehicles = Arrays.asList(
                new Bike(1),
                new Car(5, 500),
                new Truck(2, 10000, true)
        );
        assertEquals(8, VehicleTest.sumSeats(vehicles));
        assertEquals(10500, VehicleTest.sumCapacity(vehicles));
    }

    @Test
    void testLargeNumberOfVehicles() {
        List<Vehicle> largeList = new ArrayList<Vehicle>();
        for (int i = 0; i < 10000; i++) {
            largeList.add(new Car(4, 400));
        }
        assertEquals(40000, VehicleTest.sumSeats(largeList));
        assertEquals(4000000, VehicleTest.sumCapacity(largeList));
    }

    @Test
    void testEdgeCaseValues() {
        Bike zeroBike = new Bike(0);
        Car zeroCar = new Car(0, 0);
        Truck zeroTruck = new Truck(0, 0, false);
        List<Vehicle> edgeCases = Arrays.asList(zeroBike, zeroCar, zeroTruck);
        assertEquals(0, VehicleTest.sumSeats(edgeCases));
        assertEquals(0, VehicleTest.sumCapacity(edgeCases));
        assertEquals(0, VehicleTest.countTailLifts(edgeCases));
    }
}