package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.FetchingOfPossibleDestinations;
import com.malinki.pz.dal.operations.FetchingOfPossibleSources;
import com.malinki.pz.dal.operations.TicketBuying;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketDTO;

public class AirportRepository {
    public PossibleAirportsResponse addTicket(TicketDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }

    public PossibleAirportsResponse getPossibleDestinations(String src){
        FetchingOfPossibleDestinations fetchingOfPossibleDestinations = new FetchingOfPossibleDestinations(src);
        return fetchingOfPossibleDestinations.performAction();
    }

    public PossibleAirportsResponse getPossibleSources(String dest){
        FetchingOfPossibleSources fetchingOfPossibleSources = new FetchingOfPossibleSources(dest);
        return fetchingOfPossibleSources.performAction();
    }
}
