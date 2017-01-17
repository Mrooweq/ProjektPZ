package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.*;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.MalinkiSimpleResponse;
import com.malinki.pz.lib.TicketRequestDTO;

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
