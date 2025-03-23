public class BridgeMonitor extends Thread {
    private final Bridge bridge;
    private final Statistics stats;

    public BridgeMonitor(Bridge bridge, Statistics stats) {
        this.bridge = bridge;
        this.stats = stats;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10_000);
                int onBridge = bridge.getCarsOnBridge();
                int inQueue = bridge.getQueueLength();
                int total = stats.getTotalProcessed();
                double avgAll = stats.getAverageWaitOverall() / 1000.0;
                double avg10Min = stats.getAverageWaitLast10Minutes() / 1000.0;

                System.out.println("\n===== BRIDGE STATISTICS =====");
                System.out.printf("Cars currently on bridge: %d/10%n", onBridge);
                System.out.printf("Cars waiting in queue: %d%n", inQueue);
                System.out.printf("Total cars processed: %d%n", total);
                System.out.printf("Average waiting time overall: %.2f seconds%n", avgAll);
                System.out.printf("Average waiting time in last 10 minutes: %.2f seconds%n", avg10Min);
                System.out.println("============================\n");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
