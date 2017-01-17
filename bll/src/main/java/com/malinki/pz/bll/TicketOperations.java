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
        MalinkiComplexResponse malinkiComplexResponse = ticketRepository.addTicket(TicketConverter.fromTicketRequestUVMToTicketRequestDTO(ticketRequestUVM));
        TicketResponseDTO dtoResult = (TicketResponseDTO) malinkiComplexResponse.getDtoResult();
        TicketResponseUVM uvmResult = TicketConverter.fromTicketResponseDTOToTicketResponseUVM(dtoResult);
        malinkiComplexResponse.setUvmResult(uvmResult);
        return malinkiComplexResponse;
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

    @Override
    public MalinkiComplexResponse getTicketByID(int id) {
        MalinkiComplexResponse malinkiComplexResponse = ticketRepository.getTicketByID(id);
        TicketResponseDTO dtoResult = (TicketResponseDTO) malinkiComplexResponse.getDtoResult();
        TicketResponseUVM uvmResult = TicketConverter.fromTicketResponseDTOToTicketResponseUVM(dtoResult);
        malinkiComplexResponse.setUvmResult(uvmResult);
        return malinkiComplexResponse;
    }
}
