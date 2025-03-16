class TollboothMonitor implements Runnable {
    private double lastAvgCars = 0;
    private static int processed = 0;
    @Override
    public void run() {
        while (TollboothSimulation.running) {
            try {
                Thread.sleep(10000);

                int totalCars = 0;
                int minCars = Integer.MAX_VALUE;
                int maxCars = 0;

                for (int i = 0; i < TollboothSimulation.tollboothCount; i++) {
                    int queueSize = TollboothSimulation.tollboothQueues.get(i).size();
                    totalCars += queueSize;
                    minCars = Math.min(minCars, queueSize);
                    maxCars = Math.max(maxCars, queueSize);
                }

                double avgCars = (double) totalCars / TollboothSimulation.tollboothCount;
                double avgChange = avgCars - lastAvgCars;

                System.out.println("---------------------");
                System.out.println("Total Cars:   " + totalCars);
                System.out.println("Min cars:     " + minCars);
                System.out.println("Max cars:     " + maxCars);
                System.out.println("Avg cars:     " + String.format("%.2f", avgCars));
                System.out.println("Avg change:   " + String.format("%.2f", avgChange));
                System.out.println("Processed:    " + processed);
                System.out.println("---------------------");
                lastAvgCars = avgCars;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    } // Methode, die aufgerufen wird, wenn das Auto verarbeitet wird
    public static void process() {
        processed++; // plus 1
    }
}
