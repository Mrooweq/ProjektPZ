package com.malinki.pz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.bll.*;
import com.malinki.pz.lib.TicketUVM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class TicketController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private TicketOperations ticketOperations;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buyTicket(HttpServletResponse response,
                          @RequestParam String flight,
                          @RequestParam String flightClass,
                          @RequestParam String user) {

        TicketUVM ticketUVM = new TicketUVM.TicketDTOTicketDTOBuilder()
                .flight(flight)
                .flightClass(flightClass)
                .user(user)
                .build();

        int result = ticketOperations.addTicket(ticketUVM);
        
        SendPDFByEmail sendPDFByEmail = new SendPDFByEmail(ticketUVM, response);
        
        
       
        response.setStatus(result);
    }
    
    @RequestMapping(value = "/test")
    public void test(){
    	GettingImageTest test = new GettingImageTest();
    	try {
			test.getImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
