package com.malinki.pz.bll.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.bll.*;
import com.malinki.pz.bll.operations.TicketOperations;
import com.malinki.pz.bll.operations.UserOperations;
import com.malinki.pz.dal.constants.Strings;
import com.malinki.pz.lib.entity.*;
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

    public PDF addTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        TicketRequestUVM ticket = parseToTicketRequesUVM(requestBody);
        String username = ticket.getUsername();
        PDFResponse pdfResponse = null;
        MalinkiComplexResponse malinkiComplexResponse = null;

        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);
        int status;

        if(isUserValidatedProperly){
            malinkiComplexResponse = ticketOperations.addTicket(ticket);

            TicketResponseUVM uvmResult = (TicketResponseUVM) malinkiComplexResponse.getUvmResult();
            pdfResponse = generatePDF(uvmResult, true);

            if(areBothResultsOk(malinkiComplexResponse, pdfResponse))
                status = HttpServletResponse.SC_OK;
            else
                status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        else{
            status = HttpServletResponse.SC_UNAUTHORIZED;
            logger.log(Level.ERROR, Strings.USER_NOT_AUTHORIZED);
        }

        response.setStatus(status);

        if(status == HttpServletResponse.SC_OK)
            return pdfResponse.getPdf();
        else
            return new PDF();
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

        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);

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

    public PDF getPdfOfTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("authorization");

        FetchPdfForTicketRequest fetchPdfForTicketRequest = parseToTicketResponseUVM(requestBody);
        String username = fetchPdfForTicketRequest.getUsername();

        MalinkiComplexResponse malinkiComplexResponse;
        PDFResponse pdfResponse = null;
        int status;

        boolean isUserValidatedProperly = userOperations.validateUserByToken(username, token);

        if(isUserValidatedProperly){
            int id = fetchPdfForTicketRequest.getTicket().getId();
            malinkiComplexResponse = ticketOperations.getTicketByID(id);

            TicketResponseUVM ticketResponseUVM = (TicketResponseUVM) malinkiComplexResponse.getUvmResult();

            pdfResponse = generatePDF(ticketResponseUVM, false);

            if(areBothResultsOk(malinkiComplexResponse, pdfResponse))
                status = HttpServletResponse.SC_OK;
            else
                status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        else{
            status = HttpServletResponse.SC_UNAUTHORIZED;
        }

        response.setStatus(status);

        if(status == HttpServletResponse.SC_OK)
            return pdfResponse.getPdf();
        else
            return new PDF();
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
