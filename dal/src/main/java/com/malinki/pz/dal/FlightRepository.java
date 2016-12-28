package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.FetchingFlights;
import com.malinki.pz.dal.operations.FetchingOfPossibleDestinations;
import com.malinki.pz.dal.operations.FetchingOfPossibleSources;
import com.malinki.pz.dal.operations.TicketBuying;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.FlightResponse;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketDTO;

public class FlightRepository {
    public FlightResponse getFlights(FlightRequest flightRequest) {
        FetchingFlights fetchingFlights = new FetchingFlights(flightRequest);
        return fetchingFlights.performAction();
    }
}
