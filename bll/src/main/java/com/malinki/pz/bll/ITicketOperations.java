package com.malinki.pz.bll;

import com.malinki.pz.lib.*;

public interface ITicketOperations {
    public MalinkiComplexResponse addTicket(TicketRequestUVM ticketRequestUVM);
    public MalinkiComplexResponse getArchivalTickets(String username);
    public MalinkiComplexResponse getTicketByID(int id);
}