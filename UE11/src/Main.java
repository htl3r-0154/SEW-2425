class Main {
    static int autoIdCounter = 1;

    public static void main(String[] args) {
        TollboothSimulation simulation = new TollboothSimulation(3);
        long startTime = System.currentTimeMillis();
        Thread monitorThread = new Thread(new TollboothMonitor());
        monitorThread.start();

        while (System.currentTimeMillis() - startTime < TollboothSimulation.runtime) {
            if (!TollboothSimulation.running) break;

            int autoId;
            synchronized (Main.class) {
                autoId = autoIdCounter++;
            }

            int tollboothId = autoId % TollboothSimulation.tollboothCount;
            Auto auto = new Auto(autoId, tollboothId);
            try {
                TollboothSimulation.tollboothQueues.get(tollboothId).put(auto);
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        simulation.stopSimulation();
        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Simulation beendet.");
    }
}