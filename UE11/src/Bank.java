class Konto {
    private int kontostand;

    public Konto() {
        this.kontostand = 0;
    }

    public int getKontostand() {
        return kontostand;
    }

    public void setKontostand(int kontostand) {
        this.kontostand = kontostand;
    }

    public synchronized void add(int betrag) {
        this.kontostand += betrag;
    }

}

class Ueberweiser implements Runnable {
    private Konto von;
    private Konto nach;
    private static final int ANZAHL = 1000;
    private static final int BETRAG = 10;

    public Ueberweiser(Konto von, Konto nach) {
        this.von = von;
        this.nach = nach;
    }

    @Override
    public void run() {
        for (int i = 0; i < ANZAHL; i++) {
            von.add(-BETRAG);
            nach.add(BETRAG);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();

        a.add(1000); // Startguthaben für Tests

        Runnable r1 = new Ueberweiser(a, b);
        Runnable r2 = new Ueberweiser(b, c);
        Runnable r3 = new Ueberweiser(c, a);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();

        System.out.println("Kontostand a: " + a.getKontostand());
        System.out.println("Kontostand b: " + b.getKontostand());
        System.out.println("Kontostand c: " + c.getKontostand());
        System.out.println("Zeitdauer: " + (endTime - startTime) + " ms");
    }
}

public class Bank {

    public static void main() {

    }
}

/* A1.1: durchschnitt: 75,6ms (wert war unabsichtlich falsch)
* A1.2:
*   die Kontostände sind falsch da die add methode nicht atomar(=teilbar) ist, passiert es dass mehrere Threads auf die add methode zugreifen und die int werte durcheinander werfen
*   output:
*   Kontostand a: 17490
*   Kontostand b: -14340
*   Kontostand c: -6100
*   Zeitdauer: 0 ms
* A1.3:
*   Kontostand a: 10000
*   Kontostand b: 0
*   Kontostand c: 0
*   Zeitdauer: 8 ms
*
*  A1.4:
*   Kontostand a: 10000
*   Kontostand b: 0
*   Kontostand c: 0
*   Zeitdauer: 16 ms
*   A1.5:
*   Kontostand a: 1000
*   Kontostand b: 0
*   Kontostand c: 0
*   Zeitdauer: 1597 ms
* */