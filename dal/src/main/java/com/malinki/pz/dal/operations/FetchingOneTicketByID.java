package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.DatabaseSimpleResponseOperation;
import com.malinki.pz.dal.TicketMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class FetchingOneTicketByID extends DatabaseComplexResponseOperation {
    private final Logger logger = Logger.getLogger(UserRegistration.class);
    private int id;

    public FetchingOneTicketByID(int id){
        super(TicketMapper.class);
        this.id = id;
    }

    @Override
    protected MalinkiComplexResponse mainAction() {
        TicketResponseDTO ticketResponseDTO;
        MalinkiComplexResponse malinkiComplexResponse = new MalinkiComplexResponse();

        try{
            ticketResponseDTO = ((TicketMapper)mapper).getTicketByID(id);
            malinkiComplexResponse.setDtoResult(ticketResponseDTO);

            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_FOR_PDF_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKETS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return malinkiComplexResponse;
    }
}
