package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.FetchingFlights;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.FlightResponse;

public class FlightRepository {
    public FlightResponse getFlights(FlightRequest flightRequest) {
        FetchingFlights fetchingFlights = new FetchingFlights(flightRequest);
        return fetchingFlights.performAction();
    }
}
