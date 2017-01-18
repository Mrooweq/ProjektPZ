package com.malinki.pz.dal.operations.specific;

import com.malinki.pz.dal.operations.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.mappers.TicketMapper;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.TicketRequestDTO;
import com.malinki.pz.lib.entity.TicketResponseDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class TicketBuying extends DatabaseComplexResponseOperation {

    private final Logger logger = Logger.getLogger(UserRegistration.class);
    private TicketRequestDTO ticket;

    public TicketBuying(TicketRequestDTO ticket) {
        super(TicketMapper.class);
        this.ticket = ticket;
    }

    @Override
    protected MalinkiComplexResponse mainAction() {
        MalinkiComplexResponse malinkiComplexResponse = new MalinkiComplexResponse();
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();

        try{
            ((TicketMapper)mapper).addTicket(ticket);
            ticketResponseDTO = ((TicketMapper) mapper).getTicketByID(ticket.getId());

            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_BOUGHT_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR;
        }

        malinkiComplexResponse.setDtoResult(ticketResponseDTO);

        return malinkiComplexResponse;
    }
}
