package com.malinki.pz.bll.services;

import com.malinki.pz.bll.operations.FlightOperations;
import com.malinki.pz.lib.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FlightService {
    private final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private FlightOperations flightOperations;

    public List<String> getPossibleDestinations(String src, HttpServletResponse response){
        MalinkiSimpleResponse malinkiSimpleResponse = flightOperations.getPossibleDestinations(src);
        response.setStatus(malinkiSimpleResponse.getResult());

        return malinkiSimpleResponse.getResponseList();
    }

    public List<String> getPossibleSources(String dest, HttpServletResponse response){
        MalinkiSimpleResponse malinkiSimpleResponse = flightOperations.getPossibleSources(dest);
        response.setStatus(malinkiSimpleResponse.getResult());

        return malinkiSimpleResponse.getResponseList();
    }

    public List<String> getClasses(HttpServletResponse response) {
        MalinkiSimpleResponse malinkiSimpleResponse = flightOperations.getClasses();

        response.setStatus(malinkiSimpleResponse.getResult());
        return malinkiSimpleResponse.getResponseList();
    }

    public List<FlightUVM> getFlights(HttpServletResponse response,
                                      @RequestParam String dateStart,
                                      @RequestParam String dateEnd,
                                      @RequestParam String from,
                                      @RequestParam String to,
                                      @RequestParam String _class,
                                      @RequestParam String numberOfPassengers) {

        FlightToSearchUVM flightToSearchUVM = new FlightToSearchUVM.FlightUVMBuilder()
                .dateStart(dateStart.equals("") ? null : dateStart)
                .dateEnd(dateEnd.equals("") ? null : dateEnd)
                .from(from.equals("") ? null : from)
                .to(to.equals("") ? null : to)
                ._class(_class.equals("") ? null : _class)
                .numberOfPassengers(numberOfPassengers.equals("") ? null : numberOfPassengers)
                .build();

        FlightRequest flightRequest = new FlightRequest();
        flightRequest.setFlightToSearchUVM(flightToSearchUVM);

        MalinkiComplexResponse malinkiComplexResponse = flightOperations.getFlights(flightRequest);
        response.setStatus(malinkiComplexResponse.getResult());

        return (List<FlightUVM>) malinkiComplexResponse.getUvmResult();
    }
}
