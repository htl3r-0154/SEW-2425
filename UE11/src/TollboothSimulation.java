import java.util.concurrent.*;
import java.util.*;

class TollboothSimulation {
    static int tollboothCount = 7;
    static int runtime; /* in Minuten */
    static List<BlockingQueue<Auto>> tollboothQueues;
    static volatile boolean running = true;
    static List<Thread> tollboothThreads = new ArrayList<>();

    public TollboothSimulation(int runtime) {
        TollboothSimulation.runtime = runtime * 60 * 1000;
        TollboothSimulation.tollboothQueues = new ArrayList<>();
        for (int i = 0; i < tollboothCount; i++) {
            tollboothQueues.add(new LinkedBlockingQueue<>());
            int boothId = i;
            Thread thread = new Thread(() -> processTollbooth(boothId));
            tollboothThreads.add(thread);
            thread.start();
        }
    }

    private void processTollbooth(int boothId) {
        while (running) {
            try {
                Auto auto = tollboothQueues.get(boothId).poll(1, TimeUnit.SECONDS);
                if (auto != null) {
                    System.out.println("Auto " + auto.id + " an Mautstelle " + boothId + " angekommen. Verarbeitungszeit: " + auto.processTime + "ms");
                    Thread.sleep(auto.processTime);
                    System.out.println("Auto " + auto.id + " passiert Mautstelle " + boothId + ". Ticket: " + auto.ticket);
                    TollboothMonitor.process();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopSimulation() {
        running = false;
        for (BlockingQueue<Auto> queue : tollboothQueues) {
            queue.clear();
        }
        for (Thread thread : tollboothThreads) {
            thread.interrupt();
        }
    }
}