
public class Truck extends Vehicle {
    int seats;
    int capacity;
    boolean hasTailLift;

    public Truck(int seats, int capacity, boolean hasTailLift) {
        this.seats = seats;
        this.capacity = capacity;
        this.hasTailLift = hasTailLift;
    }

    public int getSeats() {
        return this.seats;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public boolean hasTailLift() {
        return this.hasTailLift;
    }

    @Override
    public String toString() {
        String tailLift;
        if (hasTailLift == true) {
            tailLift = "has tail lift";
        } else {
            tailLift = "has no tail lift";
        }
        return "Truck: seats: " + seats + ", capacity: " + capacity + " kg, " + tailLift;
    }
}
