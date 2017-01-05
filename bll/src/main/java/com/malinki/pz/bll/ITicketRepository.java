package com.malinki.pz.bll;

import com.malinki.pz.lib.TicketUVM;
import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;

public interface ITicketRepository {
    public int addTicket(TicketUVM ticketUVM);
}