package com.malinki.pz.bll.operations;

import com.malinki.pz.bll.operations.interfaces.ITicketOperations;
import com.malinki.pz.bll.converters.TicketConverter;
import com.malinki.pz.dal.repositories.TicketRepository;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.TicketRequestUVM;
import com.malinki.pz.lib.entity.TicketResponseDTO;
import com.malinki.pz.lib.entity.TicketResponseUVM;
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
