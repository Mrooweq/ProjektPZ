package com.malinki.pz.bll;

import com.malinki.pz.lib.TicketDTO;
import com.malinki.pz.lib.TicketUVM;

public class TicketConverter {

    public static TicketDTO fromTicketUVMToTicketDTO(TicketUVM ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketDTO.TicketDTOBuilder()
                    .flightNumber(ticket.getFlightNumber())
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightClass(ticket.getFlightClass())
                    .username(ticket.getUsername())
                    .build();
    }

    public static TicketUVM fromTicketDTOToTicketUVM(TicketDTO ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketUVM.TicketUVMBuilder()
                    .flightNumber(ticket.getFlightNumber())
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightClass(ticket.getFlightClass())
                    .username(ticket.getUsername())
                    .build();
    }
}
