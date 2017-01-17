package com.malinki.pz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.bll.*;
import com.malinki.pz.lib.TicketRequestUVM;
import com.malinki.pz.lib.TicketResponseUVM;
import com.malinki.pz.lib.TicketUVM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class TicketController {

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public PDFResponse addTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        return ticketService.addTicket(requestBody, request, response);
    }

    @RequestMapping(value = "/archival", method = RequestMethod.POST)
    public List<TicketResponseUVM> getArchivalTickets(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        return ticketService.getArchivalTickets(requestBody, request, response);
    }

    @RequestMapping(value = "/getpdf", method = RequestMethod.POST)
    public PDFResponse getPdfOfTicket(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {
        return ticketService.getPdfOfTicket(requestBody, request, response);
    }
}
