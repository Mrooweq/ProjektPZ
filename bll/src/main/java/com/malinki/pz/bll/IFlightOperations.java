package com.malinki.pz.bll;

import com.malinki.pz.lib.*;

public interface IFlightOperations {
    public FlightResponse getFlights(FlightRequest flightRequest);
    public MalinkiResponse addTicket(TicketUVM ticketUVM);
    public MalinkiResponse getPossibleDestinations(String src);
    public MalinkiResponse getPossibleSources(String dest);
    public MalinkiResponse getClasses();
}