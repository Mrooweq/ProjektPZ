package com.malinki.pz.bll;

import com.malinki.pz.dal.TicketRepository;
import com.malinki.pz.lib.TicketUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketOperations implements ITicketRepository {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public int addTicket(TicketUVM ticketUVM) {
        return ticketRepository.addTicket(TicketConverter.fromTicketUVMToTicketDTO(ticketUVM));
    }
}
