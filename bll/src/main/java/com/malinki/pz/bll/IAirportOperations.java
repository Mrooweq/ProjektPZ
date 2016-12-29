package com.malinki.pz.bll;

import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketUVM;
import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;

public interface IAirportOperations {
    public PossibleAirportsResponse addTicket(TicketUVM ticketUVM);
    public PossibleAirportsResponse getPossibleDestinations(String src);
    public PossibleAirportsResponse getPossibleSources(String dest);
}