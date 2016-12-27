package com.malinki.pz.controller;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.bll.*;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketUVM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class FlightController {

    private Logger logger = Logger.getLogger(UserController.class);

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

        PossibleAirportsResponse possibleAirportsResponse = flightOperations.addTicket(ticketUVM);
        response.setStatus(possibleAirportsResponse.getResult());
    }

    @RequestMapping(value = "/dest", method = RequestMethod.GET)
    public List<String> getPossibleDestinations(HttpServletResponse response, @RequestParam String src) {
        PossibleAirportsResponse possibleAirportsResponse = flightOperations.getPossibleDestinations(src);
        response.setStatus(possibleAirportsResponse.getResult());

        return possibleAirportsResponse.getPossibleAirportsList();
    }
}
