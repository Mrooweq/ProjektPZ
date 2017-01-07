package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseSearcherOperation;
import com.malinki.pz.lib.MalinkiSimpleResponse;
import com.malinki.pz.lib.TicketDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class TicketBuying extends DatabaseSearcherOperation {

    private Logger logger = Logger.getLogger(UserRegistration.class);
    private TicketDTO ticket;

    public TicketBuying(TicketDTO ticket) {
        this.ticket = ticket;
    }

    @Override
    protected MalinkiSimpleResponse mainAction() {
        try{
            flightMapper.addTicket(ticket);
            flightMapper.commit();
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_BOUGHT_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return null;
    }
}
