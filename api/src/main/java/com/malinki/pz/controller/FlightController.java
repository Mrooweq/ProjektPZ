package com.malinki.pz.controller;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.bll.*;
import com.malinki.pz.lib.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class FlightController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private AirportOperations airportOperations;

    @Autowired
    private FlightOperations flightOperations;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buyTicket(HttpServletResponse response,
                          @RequestParam String flight,
                          @RequestParam String flightClass,
                          @RequestParam String user) {

        TicketUVM ticketUVM = new TicketUVM.TicketUVMBuilder()
                .flight(flight)
                .flightClass(flightClass)
                .user(user)
                .build();

        PossibleAirportsResponse possibleAirportsResponse = airportOperations.addTicket(ticketUVM);
        response.setStatus(possibleAirportsResponse.getResult());
    }

    @RequestMapping(value = "/dest", method = RequestMethod.GET)
    public List<String> getPossibleDestinations(HttpServletResponse response, @RequestParam String src) {
        PossibleAirportsResponse possibleAirportsResponse = airportOperations.getPossibleDestinations(src);
        response.setStatus(possibleAirportsResponse.getResult());

        return possibleAirportsResponse.getPossibleAirportsList();
    }

    @RequestMapping(value = "/src", method = RequestMethod.GET)
    public List<String> getPossibleSources(HttpServletResponse response, @RequestParam String dest) {
        PossibleAirportsResponse possibleAirportsResponse = airportOperations.getPossibleSources(dest);
        response.setStatus(possibleAirportsResponse.getResult());

        return possibleAirportsResponse.getPossibleAirportsList();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<FlightUVM> getFlights(HttpServletResponse response,
                                      @RequestParam String dateStart,
                                      @RequestParam String dateEnd,
                                      @RequestParam String from,
                                      @RequestParam String to) {

        FlightToSearchUVM flightToSearchUVM = new FlightToSearchUVM.FlightUVMBuilder()
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .from(from)
                .to(to)
                .build();

        FlightRequest flightRequest = new FlightRequest();
        flightRequest.setFlightToSearchUVM(flightToSearchUVM);

        FlightResponse flightResponse = flightOperations.getFlights(flightRequest);
        response.setStatus(flightResponse.getResult());

        return flightResponse.getFlightUVMResultList();
    }
}
