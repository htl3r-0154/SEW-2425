import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Runnable task = () -> {
        TollboothSimulation simulation = new TollboothSimulation(3);
        for (int i = 0; i < TollboothSimulation.tollboothCount; i++) {
            int threadId = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 200000; j++) {
                    System.out.print("Tollbooth " + threadId);
                }
                System.out.println(" ");
            });
            thread.start();
        }
    };

    executor.submit(task);

    Thread.sleep(3000); // Let threads run for 3 seconds
    executor.shutdownNow(); // Stop all threads
}
