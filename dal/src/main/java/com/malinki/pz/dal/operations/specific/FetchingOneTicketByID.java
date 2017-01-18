package com.malinki.pz.dal.operations.specific;

import com.malinki.pz.dal.operations.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.mappers.TicketMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.TicketResponseDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
