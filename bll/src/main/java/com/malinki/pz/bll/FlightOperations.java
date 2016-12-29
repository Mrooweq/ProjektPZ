package com.malinki.pz.bll;

import com.malinki.pz.dal.AirportRepository;
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
    public FlightResponse getFlights(FlightRequest flightRequest) {
        FlightToSearchUVM flightToSearchUVM = flightRequest.getFlightToSearchUVM();
        FlightToSearchDTO flightToSearchDTO = FlightConverter.fromFlightToSearchUVMToFlightToSearchDTO(flightToSearchUVM);

        flightRequest.setFlightToSearchDTO(flightToSearchDTO);

        FlightResponse flightResponse = flightRepository.getFlights(flightRequest);

        List<FlightDTO> flightDTOResultList = flightResponse.getFlightDTOResultList();
        List<FlightUVM> flightUVMResultList = new ArrayList<>();

        for(FlightDTO flightDTO : flightDTOResultList)
            flightUVMResultList.add(FlightConverter.fromFlightDTOToFlightUVM(flightDTO));

        flightResponse.setFlightUVMResultList(flightUVMResultList);

        return flightResponse;
    }
}