
public class Bike extends Vehicle {
    int seats;
    public Bike(int seats) {
        this.seats = seats;
    }
    public int getSeats() {
        return this.seats;
    }
    @Override
    public String toString(){
        return "Bike: seats: "+seats;
    }
}
