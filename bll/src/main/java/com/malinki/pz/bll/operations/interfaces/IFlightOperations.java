package com.malinki.pz.bll.operations.interfaces;

import com.malinki.pz.lib.entity.FlightRequest;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.MalinkiSimpleResponse;

public interface IFlightOperations {
    public MalinkiComplexResponse getFlights(FlightRequest flightRequest);
    public MalinkiSimpleResponse getPossibleDestinations(String src);
    public MalinkiSimpleResponse getPossibleSources(String dest);
    public MalinkiSimpleResponse getClasses();
}