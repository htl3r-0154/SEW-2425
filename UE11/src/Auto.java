public class Auto {
    Ticket ticket;
    int arrivalTime = 500; /*zwischen 500 - 2500ms*/
    int processTime = 2 * 1000;/*zwischen 2-20s*/
    public Auto(){
        this.arrivalTime = (int) ((Math.random()*2000) + 500);
        this.processTime = (int) ((Math.random()*18000) + 2000);
    }
}
