public abstract class Vehicle {
    private int seats;
    private double capacity;
    private boolean tailLift;

    public abstract int getSeats();

    public double getCapacity(){
        return capacity;
    }
    public boolean hasTailLift(){
        return tailLift;
    }
}
