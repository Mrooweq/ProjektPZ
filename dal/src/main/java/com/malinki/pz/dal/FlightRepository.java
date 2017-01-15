package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.*;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.MalinkiSimpleResponse;
import com.malinki.pz.lib.TicketRequestDTO;

public class FlightRepository {
    public MalinkiComplexResponse getFlights(FlightRequest flightRequest) {
        FetchingFlights fetchingFlights = new FetchingFlights(flightRequest);
        return fetchingFlights.performAction();
    }

    public MalinkiSimpleResponse getPossibleDestinations(String src){
        FetchingOfPossibleDestinations fetchingOfPossibleDestinations = new FetchingOfPossibleDestinations(src);
        return fetchingOfPossibleDestinations.performAction();
    }

    public MalinkiSimpleResponse getPossibleSources(String dest){
        FetchingOfPossibleSources fetchingOfPossibleSources = new FetchingOfPossibleSources(dest);
        return fetchingOfPossibleSources.performAction();
    }

    public MalinkiSimpleResponse getClasses(){
        FetchingOfClasses fetchingOfClasses = new FetchingOfClasses();
        return fetchingOfClasses.performAction();
    }
}
