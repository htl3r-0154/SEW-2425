
public class Car extends Vehicle {
    int seats;
    int capacity;

    public Car(int seats, int capacity){
        this.seats = seats;
        this.capacity = capacity;
    }
    public int getSeats() {
        return this.seats;
    }

    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString(){
        return "Car: seats: "+seats+", capacity: "+capacity+" kg";
    }
}
