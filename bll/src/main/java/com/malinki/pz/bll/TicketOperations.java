package com.malinki.pz.bll;

import com.malinki.pz.dal.FlightRepository;
import com.malinki.pz.dal.TicketRepository;
import com.malinki.pz.lib.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketOperations implements ITicketOperations {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public MalinkiComplexResponse addTicket(TicketRequestUVM ticketRequestUVM) {
        return ticketRepository.addTicket(TicketConverter.fromTicketRequestUVMToTicketRequestDTO(ticketRequestUVM));
    }

    @Override
    public MalinkiComplexResponse getArchivalTickets(String username) {
        MalinkiComplexResponse malinkiComplexResponse = ticketRepository.getArchivalTickets(username);

        List<TicketResponseDTO> dtoResultList = (List<TicketResponseDTO>) malinkiComplexResponse.getDtoResult();

        List<TicketResponseUVM> uvmResultList = new ArrayList<>();

        for(TicketResponseDTO ticketResponseDTO : dtoResultList)
            uvmResultList.add(TicketConverter.fromTicketResponseDTOToTicketResponseUVM(ticketResponseDTO));

        malinkiComplexResponse.setUvmResult(uvmResultList);

        return malinkiComplexResponse;
    }
}
