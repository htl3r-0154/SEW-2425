class TicketGenerator {
    private static int ticketCounter = 1;

    public static synchronized Ticket generateTicket(int autoId, int tollboothId) {
        return new Ticket(ticketCounter++, autoId, tollboothId);
    }
}