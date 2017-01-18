package com.malinki.pz.bll.operations;

import com.malinki.pz.bll.operations.interfaces.IFlightOperations;
import com.malinki.pz.bll.converters.FlightConverter;
import com.malinki.pz.dal.repositories.FlightRepository;
import com.malinki.pz.lib.entity.*;
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
}
