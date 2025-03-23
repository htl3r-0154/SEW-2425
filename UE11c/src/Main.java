public class Main {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        Statistics stats = new Statistics();

        new CarGenerator(bridge, stats).start();
        new BridgeMonitor(bridge, stats).start();
    }
}
