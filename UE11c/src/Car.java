public class Car extends Thread {
    private static int idCounter = 1;
    private final int id;
    private final Bridge bridge;
    private long waitTime;

    public Car(Bridge bridge) {
        this.id = idCounter++;
        this.bridge = bridge;
    }

    public int getCarId() {
        return id;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car " + id + " is waiting to cross the bridge");
            bridge.enterBridge(this);
            System.out.println("Car " + id + " is crossing the bridge");
            Thread.sleep(50_000); // 50 Sek Überfahrt
            System.out.println("Car " + id + " has left the bridge");
            bridge.leaveBridge(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
