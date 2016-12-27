package com.malinki.pz.bll;

import com.malinki.pz.dal.FlightRepository;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightOperations implements ITicketOperations {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public PossibleAirportsResponse addTicket(TicketUVM ticketUVM) {
        return flightRepository.addTicket(TicketConverter.fromTicketUVMToTicketDTO(ticketUVM));
    }

    @Override
    public PossibleAirportsResponse getPossibleDestinations(String src) {
        return flightRepository.getPossibleDestinations(src);
    }

    @Override
    public PossibleAirportsResponse getPossibleSources(String dest) {
        return null;
    }
}
