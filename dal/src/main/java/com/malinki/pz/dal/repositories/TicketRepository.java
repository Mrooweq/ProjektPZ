package com.malinki.pz.dal.repositories;

import com.malinki.pz.dal.operations.specific.FetchingArchivalTickets;
import com.malinki.pz.dal.operations.specific.FetchingOneTicketByID;
import com.malinki.pz.dal.operations.specific.TicketBuying;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.TicketRequestDTO;

public class TicketRepository {
    public MalinkiComplexResponse addTicket(TicketRequestDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }

    public MalinkiComplexResponse getArchivalTickets(String username){
        FetchingArchivalTickets fetchingArchivalTickets = new FetchingArchivalTickets(username);
        return fetchingArchivalTickets.performAction();
    }

    public MalinkiComplexResponse getTicketByID(int id){
        FetchingOneTicketByID fetchingOneTicketByID = new FetchingOneTicketByID(id);
        return fetchingOneTicketByID.performAction();
    }
}
