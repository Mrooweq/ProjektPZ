package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.TicketMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class FetchingArchivalTickets extends DatabaseComplexResponseOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);
    private String username;

    public FetchingArchivalTickets(String username){
        this.username = username;
    }

    @Override
    protected MalinkiComplexResponse mainAction() {
        List<TicketResponseDTO> ticketRequestDTOResultList;
        MalinkiComplexResponse malinkiComplexResponse = new MalinkiComplexResponse();

        try{
            ticketRequestDTOResultList = ((TicketMapper)mapper).getArchivalTickets(username);
            malinkiComplexResponse.setDtoResult(ticketRequestDTOResultList);

            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKETS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKETS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return malinkiComplexResponse;
    }
}
