package com.malinki.pz.dal.repositories;

import com.malinki.pz.dal.operations.specific.FetchingFlights;
import com.malinki.pz.dal.operations.specific.FetchingOfClasses;
import com.malinki.pz.dal.operations.specific.FetchingOfPossibleDestinations;
import com.malinki.pz.dal.operations.specific.FetchingOfPossibleSources;
import com.malinki.pz.lib.entity.FlightRequest;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.MalinkiSimpleResponse;

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
