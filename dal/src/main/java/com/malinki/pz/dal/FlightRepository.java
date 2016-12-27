package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.FetchingOfPossibleAirports;
import com.malinki.pz.dal.operations.TicketBuying;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketDTO;

public class FlightRepository {
    public PossibleAirportsResponse addTicket(TicketDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }

    public PossibleAirportsResponse getPossibleDestinations(String src){
        FetchingOfPossibleAirports fetchingOfPossibleAirports = new FetchingOfPossibleAirports(src);
        return fetchingOfPossibleAirports.performAction();
    }
}
