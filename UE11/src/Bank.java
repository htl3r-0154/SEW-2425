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

    public void add(int betrag) {
        int wert = getKontostand();
        wert = wert + betrag;
        setKontostand(wert);
    }

}
class Ueberweiser {
    private Konto von;
    private Konto nach;
    private static final int ANZAHL = 10_000_000;
    private static final int BETRAG = 10;

    public Ueberweiser(Konto von, Konto nach) {
        this.von = von;
        this.nach = nach;
    }

    public void run() {
        for (int i = 0; i < ANZAHL; i++) {
            von.add(-BETRAG);
            nach.add(BETRAG);
        }
    }
    public static void main(String[] args) {
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();

        a.add(100_000_000);

        Ueberweiser ab = new Ueberweiser(a, b);
        Ueberweiser bc = new Ueberweiser(b, c);
        Ueberweiser ca = new Ueberweiser(c, a);

        long startTime = System.currentTimeMillis();
        ab.run();
        bc.run();
        ca.run();
        long endTime = System.currentTimeMillis();

        System.out.println("Kontostand a: " + a.getKontostand());
        System.out.println("Kontostand b: " + b.getKontostand());
        System.out.println("Kontostand c: " + c.getKontostand());
        System.out.println("Zeitdauer: " + (endTime - startTime) + " ms");
    }
}
public class Bank {

    public static void main(){

    }
}

// Zeitdauer1: durchschnitt: 75,6ms