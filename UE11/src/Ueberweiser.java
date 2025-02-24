public class Ueberweiser implements Runnable {

    private Konto von;
    private Konto nach;


    private static final int ANZAHL = 10_000_000;
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
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();


        Ueberweiser u1 = new Ueberweiser(a, b);
        Ueberweiser u2 = new Ueberweiser(b, c);
        Ueberweiser u3 = new Ueberweiser(c, a);

        long start = System.currentTimeMillis();


        Thread t1 = new Thread(u1);
        Thread t2 = new Thread(u2);
        Thread t3 = new Thread(u3);

        t1.start();
        t2.start();
        t3.start();


        t1.join();
        t2.join();
        t3.join();


        long ende = System.currentTimeMillis();
        long dauer = ende - start;


        System.out.println("Zeitdauer: " + dauer + " ms");


        System.out.println("Kontostand a: " + a.getKontostand());
        System.out.println("Kontostand b: " + b.getKontostand());
        System.out.println("Kontostand c: " + c.getKontostand());
    }
}
