package com.malinki.pz.bll;

import com.malinki.pz.dal.AirportRepository;
import com.malinki.pz.lib.PossibleAirportsResponse;
import com.malinki.pz.lib.TicketUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportOperations implements IAirportOperations {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public PossibleAirportsResponse addTicket(TicketUVM ticketUVM) {
        return airportRepository.addTicket(TicketConverter.fromTicketUVMToTicketDTO(ticketUVM));
    }

    @Override
    public PossibleAirportsResponse getPossibleDestinations(String src) {
        return airportRepository.getPossibleDestinations(src);
    }

    @Override
    public PossibleAirportsResponse getPossibleSources(String dest) {
        return airportRepository.getPossibleSources(dest);
    }
}
