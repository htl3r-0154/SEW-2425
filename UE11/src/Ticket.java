class Ticket {
    int ticketId;
    int tollboothId;

    public Ticket(int ticketId, int autoId, int tollboothId) {
        this.ticketId = ticketId;
        this.tollboothId = tollboothId;
    }

    @Override
    public String toString() {
        return " ticketId=" + ticketId + ", tollboothId=" + tollboothId;
    }
}