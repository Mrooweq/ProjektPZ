package com.malinki.pz.bll.operations.interfaces;

import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.TicketRequestUVM;

public interface ITicketOperations {
    public MalinkiComplexResponse addTicket(TicketRequestUVM ticketRequestUVM);
    public MalinkiComplexResponse getArchivalTickets(String username);
    public MalinkiComplexResponse getTicketByID(int id);
}