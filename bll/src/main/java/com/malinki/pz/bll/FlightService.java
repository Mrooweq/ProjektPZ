package com.malinki.pz.bll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FlightService {
    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private FlightOperations flightOperations;

    public List<String> getPossibleDestinations(String src, HttpServletResponse response){
        MalinkiResponse malinkiResponse = flightOperations.getPossibleDestinations(src);
        response.setStatus(malinkiResponse.getResult());

        return malinkiResponse.getResponseList();
    }

    public List<String> getPossibleSources(String dest, HttpServletResponse response){
        MalinkiResponse malinkiResponse = flightOperations.getPossibleSources(dest);
        response.setStatus(malinkiResponse.getResult());

        return malinkiResponse.getResponseList();
    }

    public List<String> getClasses(HttpServletResponse response) {
        MalinkiResponse malinkiResponse = flightOperations.getClasses();

        response.setStatus(malinkiResponse.getResult());
        return malinkiResponse.getResponseList();
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

        FlightResponse flightResponse = flightOperations.getFlights(flightRequest);
        response.setStatus(flightResponse.getResult());

        return flightResponse.getFlightUVMResultList();
    }

    public void buyTicket(@RequestBody String requestBody, HttpServletResponse response) {
        MalinkiResponse malinkiResponse = flightOperations.addTicket(parseToTicketUVM(requestBody));
        response.setStatus(malinkiResponse.getResult());
    }

    private TicketUVM parseToTicketUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TicketUVM user = null;

        try {
            user = mapper.readValue(requestBody, TicketUVM.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return user;
    }
}
