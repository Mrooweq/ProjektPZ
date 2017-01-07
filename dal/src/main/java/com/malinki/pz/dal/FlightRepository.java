package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.*;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.FlightResponse;
import com.malinki.pz.lib.MalinkiResponse;
import com.malinki.pz.lib.TicketDTO;

public class FlightRepository {
    public FlightResponse getFlights(FlightRequest flightRequest) {
        FetchingFlights fetchingFlights = new FetchingFlights(flightRequest);
        return fetchingFlights.performAction();
    }

    public MalinkiResponse addTicket(TicketDTO ticket) {
        TicketBuying ticketBuying = new TicketBuying(ticket);
        return ticketBuying.performAction();
    }

    public MalinkiResponse getPossibleDestinations(String src){
        FetchingOfPossibleDestinations fetchingOfPossibleDestinations = new FetchingOfPossibleDestinations(src);
        return fetchingOfPossibleDestinations.performAction();
    }

    public MalinkiResponse getPossibleSources(String dest){
        FetchingOfPossibleSources fetchingOfPossibleSources = new FetchingOfPossibleSources(dest);
        return fetchingOfPossibleSources.performAction();
    }

    public MalinkiResponse getClasses(){
        FetchingOfClasses FetchingOfClasses = new FetchingOfClasses();
        return FetchingOfClasses.performAction();
    }
}
