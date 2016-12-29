package com.malinki.pz.bll;

import com.malinki.pz.lib.*;

public interface IAirportOperations {
    public ProjektPZResponse addTicket(TicketUVM ticketUVM);
    public ProjektPZResponse getPossibleDestinations(String src);
    public ProjektPZResponse getPossibleSources(String dest);
    public ProjektPZResponse getClasses();
}