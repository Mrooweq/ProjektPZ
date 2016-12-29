package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.FetchingOfClasses;
import com.malinki.pz.dal.operations.FetchingOfPossibleDestinations;
import com.malinki.pz.dal.operations.FetchingOfPossibleSources;
import com.malinki.pz.dal.operations.TicketBuying;
import com.malinki.pz.lib.ProjektPZResponse;
import com.malinki.pz.lib.TicketDTO;

public class AirportRepository {
    public ProjektPZResponse addTicket(TicketDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }

    public ProjektPZResponse getPossibleDestinations(String src){
        FetchingOfPossibleDestinations fetchingOfPossibleDestinations = new FetchingOfPossibleDestinations(src);
        return fetchingOfPossibleDestinations.performAction();
    }

    public ProjektPZResponse getPossibleSources(String dest){
        FetchingOfPossibleSources fetchingOfPossibleSources = new FetchingOfPossibleSources(dest);
        return fetchingOfPossibleSources.performAction();
    }

    public ProjektPZResponse getClasses(){
        FetchingOfClasses FetchingOfClasses = new FetchingOfClasses();
        return FetchingOfClasses.performAction();
    }
}
