package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.TicketBuying;
import com.malinki.pz.lib.TicketDTO;

public class TicketRepository {
    public int addTicket(TicketDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }
}
