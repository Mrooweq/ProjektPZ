package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseTicketOperation;
import com.malinki.pz.lib.TicketDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class TicketBuying extends DatabaseTicketOperation {

    private Logger logger = Logger.getLogger(UserRegistration.class);
    private TicketDTO ticket;

    public TicketBuying(TicketDTO ticket) {
        this.ticket = ticket;
    }

    @Override
    protected void mainAction() {
        try{
            mapper.addTicket(ticket.getFlight(), ticket.getFlightClass(), ticket.getUser());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_BOUGHT_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR;
        }
    }
}