import java.util.Random;

public class CarGenerator extends Thread {
    private final Bridge bridge;
    private final Statistics stats;
    private final Random rand = new Random();

    public CarGenerator(Bridge bridge, Statistics stats) {
        this.bridge = bridge;
        this.stats = stats;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Car car = new Car(bridge);
                car.start();
                stats.registerCar(car);
                Thread.sleep((1 + rand.nextInt(5)) * 1000); // 1-5 Sekunden
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
