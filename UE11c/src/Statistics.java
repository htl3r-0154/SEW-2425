import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Statistics {
    private final List<Long> allWaitTimes = Collections.synchronizedList(new LinkedList<>());
    private final List<Car> recentCars = Collections.synchronizedList(new LinkedList<>());

    public void registerCar(Car car) {
        synchronized (recentCars) {
            allWaitTimes.add(car.getWaitTime());
            recentCars.add(car);
            long cutoff = System.currentTimeMillis() - 10 * 60 * 1000;
            recentCars.removeIf(c -> (System.currentTimeMillis() - c.getWaitTime()) > cutoff);
        }
    }

    public double getAverageWaitOverall() {
        synchronized (allWaitTimes) {
            if (allWaitTimes.isEmpty()) return 0;
            return allWaitTimes.stream().mapToLong(Long::longValue).average().orElse(0);
        }
    }

    public double getAverageWaitLast10Minutes() {
        synchronized (recentCars) {
            if (recentCars.isEmpty()) return 0;
            return recentCars.stream().mapToLong(Car::getWaitTime).average().orElse(0);
        }
    }

    public int getTotalProcessed() {
        synchronized (allWaitTimes) {
            return allWaitTimes.size();
        }
    }
}
