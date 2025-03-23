import java.util.LinkedList;
import java.util.Queue;

public class Bridge {
    private final int MAX_CARS = 10;
    private int carsOnBridge = 0;
    private final Queue<Car> waitingQueue = new LinkedList<>();

    public synchronized void enterBridge(Car car) throws InterruptedException {
        waitingQueue.add(car);
        long startWait = System.currentTimeMillis();

        while (waitingQueue.peek() != car || carsOnBridge >= MAX_CARS) {
            wait();
        }

        waitingQueue.poll(); // vorne in Warteschlange, darf fahren
        carsOnBridge++;
        car.setWaitTime(System.currentTimeMillis() - startWait);
        notifyAll(); // wichtig: Monitor & neue Threads
    }

    public synchronized void leaveBridge(Car car) {
        carsOnBridge--;
        notifyAll();
    }

    public synchronized int getCarsOnBridge() {
        return carsOnBridge;
    }

    public synchronized int getQueueLength() {
        return waitingQueue.size();
    }
}
