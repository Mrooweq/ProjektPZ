package com.malinki.pz.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.bll.*;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class FlightController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private AirportOperations airportOperations;

    @Autowired
    private FlightOperations flightOperations;

    @RequestMapping(value = "/dest", method = RequestMethod.GET)
    public List<String> getPossibleDestinations(HttpServletResponse response, @RequestParam String src) {
        ProjektPZResponse projektPZResponse = airportOperations.getPossibleDestinations(src);
        response.setStatus(projektPZResponse.getResult());

        return projektPZResponse.getResponseList();
    }

    @RequestMapping(value = "/src", method = RequestMethod.GET)
    public List<String> getPossibleSources(HttpServletResponse response, @RequestParam String dest) {
        ProjektPZResponse projektPZResponse = airportOperations.getPossibleSources(dest);
        response.setStatus(projektPZResponse.getResult());

        return projektPZResponse.getResponseList();
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public List<String> getClasses(HttpServletResponse response) {
        ProjektPZResponse projektPZResponse = airportOperations.getClasses();

        response.setStatus(projektPZResponse.getResult());
        return projektPZResponse.getResponseList();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<FlightUVM> getFlights(HttpServletResponse response,
                                      @RequestParam String dateStart,
                                      @RequestParam String dateEnd,
                                      @RequestParam String from,
                                      @RequestParam String to,
                                      @RequestParam String _class,
                                      @RequestParam String numberOfPassengers) {

        FlightToSearchUVM flightToSearchUVM = new FlightToSearchUVM.FlightUVMBuilder()
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .from(from)
                .to(to)
                ._class(_class)
                .numberOfPassengers(numberOfPassengers)
                .build();

        FlightRequest flightRequest = new FlightRequest();
        flightRequest.setFlightToSearchUVM(flightToSearchUVM);

        FlightResponse flightResponse = flightOperations.getFlights(flightRequest);
        response.setStatus(flightResponse.getResult());

        return flightResponse.getFlightUVMResultList();
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyTicket(HttpServletResponse response, @RequestBody String requestBody) {
        ProjektPZResponse projektPZResponse = airportOperations.addTicket(parseToTicketUVM(requestBody));
        response.setStatus(projektPZResponse.getResult());
    }

    public TicketUVM parseToTicketUVM(String requestBody) {
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
