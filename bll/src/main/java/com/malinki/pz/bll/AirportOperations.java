package com.malinki.pz.bll;

import com.malinki.pz.dal.AirportRepository;
import com.malinki.pz.lib.ProjektPZResponse;
import com.malinki.pz.lib.TicketUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportOperations implements IAirportOperations {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public ProjektPZResponse addTicket(TicketUVM ticketUVM) {
        return airportRepository.addTicket(TicketConverter.fromTicketUVMToTicketDTO(ticketUVM));
    }

    @Override
    public ProjektPZResponse getPossibleDestinations(String src) {
        return airportRepository.getPossibleDestinations(src);
    }

    @Override
    public ProjektPZResponse getPossibleSources(String dest) {
        return airportRepository.getPossibleSources(dest);
    }

    @Override
    public ProjektPZResponse getClasses() {
        return airportRepository.getClasses();
    }
}
