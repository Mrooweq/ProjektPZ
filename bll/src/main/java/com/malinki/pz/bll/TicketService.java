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
    private final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private TicketOperations ticketOperations;

    @Autowired
    private UserOperations userOperations;

    public PDFResponse addTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        TicketRequestUVM ticket = parseToTicketRequesUVM(requestBody);
        String username = ticket.getUsername();
        PDFResponse pdfResponse = null;
        MalinkiComplexResponse malinkiComplexResponse = null;

//        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);
        boolean isUserValidatedProperly = true;

        if(isUserValidatedProperly){
            malinkiComplexResponse = ticketOperations.addTicket(ticket);

            TicketResponseUVM uvmResult = (TicketResponseUVM) malinkiComplexResponse.getUvmResult();
            pdfResponse = generatePDF(uvmResult, true);
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.log(Level.ERROR, Strings.USER_NOT_AUTHORIZED);
        }

        if(areBothResultsOk(malinkiComplexResponse, pdfResponse))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        return pdfResponse;
    }

    private boolean areBothResultsOk(MalinkiComplexResponse malinkiComplexResponse, PDFResponse pdfResponse){
        int resultOk = HttpServletResponse.SC_OK;

        return malinkiComplexResponse != null
                && pdfResponse != null
                && malinkiComplexResponse.getResult() == resultOk
                && pdfResponse.getResult() == resultOk;
    }

    public List<TicketResponseUVM> getArchivalTickets(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
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

        return (List<TicketResponseUVM>) malinkiSimpleResponse.getUvmResult();
    }

    public PDFResponse getPdfOfTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        FetchPdfForTicketRequest fetchPdfForTicketRequest = parseToTicketResponseUVM(requestBody);
        String username = fetchPdfForTicketRequest.getUsername();

        MalinkiComplexResponse malinkiComplexResponse;
        PDFResponse pdfResponse;

//        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);
        boolean isUserValidatedProperly = true;

        if(isUserValidatedProperly){
            int id = fetchPdfForTicketRequest.getTicket().getId();
            malinkiComplexResponse = ticketOperations.getTicketByID(id);

            TicketResponseUVM ticketResponseUVM = (TicketResponseUVM) malinkiComplexResponse.getUvmResult();

            pdfResponse = generatePDF(ticketResponseUVM, false);
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        if(areBothResultsOk(malinkiComplexResponse, pdfResponse))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        return pdfResponse;
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

    private FetchPdfForTicketRequest parseToTicketResponseUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        FetchPdfForTicketRequest request = null;

        try {
            request = mapper.readValue(requestBody, FetchPdfForTicketRequest.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return request;
    }

    private TicketRequestUVM parseToTicketRequesUVM(String requestBody) {
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

    private PDFResponse generatePDF(TicketResponseUVM uvmResult, boolean ifSend){
        PDFResponse pdfResponse = null;
        PDFGenerator pdfGenerator = new PDFGenerator();

        try {
            pdfResponse = pdfGenerator.generatePDF(uvmResult, ifSend);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.toString());
        }

        return pdfResponse;
    }
}
