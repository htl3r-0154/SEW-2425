public class Konto {
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


    public static void main(String[] args) {
        Konto meinKonto = new Konto();
        System.out.println("Anfangskontostand: " + meinKonto.getKontostand());
        meinKonto.add(100);
        System.out.println("Nach Einzahlung 100: " + meinKonto.getKontostand());
    }
}
