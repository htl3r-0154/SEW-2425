class Auto {
    Ticket ticket;
    int id;
    int arrivalTime; /*zwischen 500 - 2500ms*/
    int processTime; /*zwischen 2-20s*/
    boolean processed; // Flag, um anzugeben, ob das Auto verarbeitet wurde

    public Auto(int id, int tollboothId) {
        this.id = id;
        this.arrivalTime = (int) ((Math.random() * 2000) + 500);
        this.processTime = (int) ((Math.random() * 18000) + 2000);
        this.ticket = TicketGenerator.generateTicket(id, tollboothId);
        this.processed = false; // Initial auf false gesetzt
    }
}
