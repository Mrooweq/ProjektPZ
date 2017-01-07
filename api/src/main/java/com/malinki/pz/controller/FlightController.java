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

    @Autowired
    private FlightService flightService;

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
    public void buyTicket(@RequestBody String requestBody, HttpServletResponse response) {
        flightService.buyTicket(requestBody, response);
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
}
