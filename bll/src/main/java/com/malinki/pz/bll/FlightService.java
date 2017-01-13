package com.malinki.pz.bll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FlightService {
    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private FlightOperations flightOperations;

    @Autowired
    private UserOperations userOperations;

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

    public void buyTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        TicketRequestUVM ticket = parseToTicketUVM(requestBody);
        String username = ticket.getUsername();

        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);

        if(isUserValidatedProperly){
            MalinkiSimpleResponse malinkiSimpleResponse = flightOperations.addTicket(ticket);
            response.setStatus(malinkiSimpleResponse.getResult());
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public List<TicketRequestUVM> getArchivalTickets(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        UserUVM userUVM = parseToUserUVM(requestBody);
        String username = userUVM.getUsername();

        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);

        MalinkiComplexResponse malinkiSimpleResponse;

        if(isUserValidatedProperly){
            malinkiSimpleResponse = flightOperations.getArchivalTickets(username);
            response.setStatus(malinkiSimpleResponse.getResult());
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        return (List<TicketRequestUVM>) malinkiSimpleResponse.getUvmResult();
    }

    private TicketRequestUVM parseToTicketUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BuyTicketResponse buyTicketResponse = null;

        try {
            buyTicketResponse = mapper.readValue(requestBody, BuyTicketResponse.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        FlightUVM flightUVM = buyTicketResponse.getFlight();
        UserUVM userUVM = buyTicketResponse.getUser();
        FlightClass flightClass = buyTicketResponse.getFlightClass();

        TicketRequestUVM ticketRequestUVM = new TicketRequestUVM.TicketUVMBuilder()
                .numberOfPlaces(flightUVM.getNumberOfPlaces())
                .flightNumber(flightUVM.getFlightNumber())
                .airlineShortcut(flightUVM.getAirlineShortcut())
                .flightClass("VIP")   // do usuniecia
                .username(userUVM.getUsername())
                .build();

        return ticketRequestUVM;
    }

    private UserUVM parseToUserUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UserUVM user = null;

        try {
            user = mapper.readValue(requestBody, UserUVM.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return user;
    }
}
