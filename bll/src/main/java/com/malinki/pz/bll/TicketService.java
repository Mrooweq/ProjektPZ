package com.malinki.pz.bll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.dal.constants.Strings;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TicketService {
    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private TicketOperations ticketOperations;

    @Autowired
    private UserOperations userOperations;

    @Autowired
    private SendPDFByEmail sendPDFByEmail;

    public void addTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        TicketRequestUVM ticket = parseToTicketUVM(requestBody);
        String username = ticket.getUsername();

//        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);
        boolean isUserValidatedProperly = true;

        if(isUserValidatedProperly){
            MalinkiComplexResponse malinkiComplexResponse = ticketOperations.addTicket(ticket);
            response.setStatus(malinkiComplexResponse.getResult());

            TicketResponseUVM uvmResult = (TicketResponseUVM) malinkiComplexResponse.getUvmResult();
            sendPdfByEmail(uvmResult, response);
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.log(Level.ERROR, Strings.USER_NOT_AUTHORIZED);
        }
    }

    public List<TicketRequestUVM> getArchivalTickets(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        UserUVM userUVM = parseToUserUVM(requestBody);
        String username = userUVM.getUsername();

//        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);
        boolean isUserValidatedProperly = true;

        MalinkiComplexResponse malinkiSimpleResponse;

        if(isUserValidatedProperly){
            malinkiSimpleResponse = ticketOperations.getArchivalTickets(username);
            response.setStatus(malinkiSimpleResponse.getResult());
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        return (List<TicketRequestUVM>) malinkiSimpleResponse.getUvmResult();
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

    private TicketRequestUVM parseToTicketUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BuyTicketRequest buyTicketRequest = null;

        try {
            buyTicketRequest = mapper.readValue(requestBody, BuyTicketRequest.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        FlightUVM flightUVM = buyTicketRequest.getFlight();
        UserUVM userUVM = buyTicketRequest.getUser();

        TicketRequestUVM ticketRequestUVM = new TicketRequestUVM.TicketUVMBuilder()
                .flightNumber(flightUVM.getFlightNumber())
                .airlineShortcut(flightUVM.getAirlineShortcut())
                .flightClass(flightUVM.get_class())
                .numberOfPlaces(flightUVM.getNumberOfPlaces())
                .username(userUVM.getUsername())
                .build();

        return ticketRequestUVM;
    }

    private void sendPdfByEmail(TicketResponseUVM uvmResult, HttpServletResponse response){
        try {
            sendPDFByEmail.sendEmail(uvmResult, response);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.toString());
        }
    }
}
