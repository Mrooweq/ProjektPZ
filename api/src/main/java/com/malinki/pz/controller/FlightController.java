package com.malinki.pz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.bll.*;
import com.malinki.pz.lib.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightOperations flightOperations;

    @RequestMapping(value = "/dest", method = RequestMethod.GET)
    public List<String> getPossibleDestinations(@RequestParam String src, HttpServletResponse response) {
        return flightService.getPossibleDestinations(src, response);
    }

    @RequestMapping(value = "/src", method = RequestMethod.GET)
    public List<String> getPossibleSources(@RequestParam String dest, HttpServletResponse response) {
        return flightService.getPossibleSources(dest, response);
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public List<String> getClasses(HttpServletResponse response) {
        return flightService.getClasses(response);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        flightService.buyTicket(requestBody, request, response);
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<FlightUVM> getFlights(HttpServletResponse response,
                                      @RequestParam String dateStart,
                                      @RequestParam String dateEnd,
                                      @RequestParam String from,
                                      @RequestParam String to,
                                      @RequestParam String _class,
                                      @RequestParam String numberOfPassengers) {

        return flightService.getFlights(response, dateStart, dateEnd, from, to, _class, numberOfPassengers);
    }

    @RequestMapping(value = "/archival", method = RequestMethod.POST)
    public List<TicketRequestUVM> getArchivalTickets(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        return flightService.getArchivalTickets(requestBody, request, response);
    }
}
