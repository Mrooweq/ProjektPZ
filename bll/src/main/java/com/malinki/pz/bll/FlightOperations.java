package com.malinki.pz.bll;

import com.malinki.pz.dal.FlightRepository;
import com.malinki.pz.lib.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightOperations implements IFlightOperations {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public MalinkiComplexResponse getFlights(FlightRequest flightRequest) {
        FlightToSearchUVM flightToSearchUVM = flightRequest.getFlightToSearchUVM();
        FlightToSearchDTO flightToSearchDTO = FlightConverter.fromFlightToSearchUVMToFlightToSearchDTO(flightToSearchUVM);

        flightRequest.setFlightToSearchDTO(flightToSearchDTO);

        MalinkiComplexResponse malinkiComplexResponse = flightRepository.getFlights(flightRequest);

        List<FlightDTO> flightDTOResultList = (List<FlightDTO>) malinkiComplexResponse.getDtoResult();
        List<FlightUVM> flightUVMResultList = new ArrayList<>();

        for(FlightDTO flightDTO : flightDTOResultList)
            flightUVMResultList.add(FlightConverter.fromFlightDTOToFlightUVM(flightDTO));

        malinkiComplexResponse.setUvmResult(flightUVMResultList);

        return malinkiComplexResponse;
    }

    @Override
    public MalinkiSimpleResponse addTicket(TicketRequestUVM ticketRequestUVM) {
        return flightRepository.addTicket(TicketConverter.fromTicketRequestUVMToTicketRequestDTO(ticketRequestUVM));
    }

    @Override
    public MalinkiSimpleResponse getPossibleDestinations(String src) {
        return flightRepository.getPossibleDestinations(src);
    }

    @Override
    public MalinkiSimpleResponse getPossibleSources(String dest) {
        return flightRepository.getPossibleSources(dest);
    }

    @Override
    public MalinkiSimpleResponse getClasses() {
        return flightRepository.getClasses();
    }

    @Override
    public MalinkiComplexResponse getArchivalTickets(String username) {
        MalinkiComplexResponse malinkiComplexResponse = flightRepository.getArchivalTickets(username);

        List<TicketResponseDTO> dtoResultList = (List<TicketResponseDTO>) malinkiComplexResponse.getDtoResult();

        List<TicketResponseUVM> uvmResultList = new ArrayList<>();

        for(TicketResponseDTO ticketResponseDTO : dtoResultList)
            uvmResultList.add(TicketConverter.fromTicketResponseDTOToTicketResponseUVM(ticketResponseDTO));

        malinkiComplexResponse.setUvmResult(uvmResultList);

        return malinkiComplexResponse;
    }
}
