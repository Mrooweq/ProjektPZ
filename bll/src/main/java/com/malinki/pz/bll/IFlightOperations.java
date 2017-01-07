package com.malinki.pz.bll;

import com.malinki.pz.lib.*;

public interface IFlightOperations {
    public MalinkiComplexResponse getFlights(FlightRequest flightRequest);
    public MalinkiSimpleResponse addTicket(TicketUVM ticketUVM);
    public MalinkiSimpleResponse getPossibleDestinations(String src);
    public MalinkiSimpleResponse getPossibleSources(String dest);
    public MalinkiSimpleResponse getClasses();
}