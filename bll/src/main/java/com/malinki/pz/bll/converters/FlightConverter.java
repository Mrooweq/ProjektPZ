package com.malinki.pz.bll.converters;

import com.malinki.pz.lib.entity.FlightDTO;
import com.malinki.pz.lib.entity.FlightToSearchDTO;
import com.malinki.pz.lib.entity.FlightToSearchUVM;
import com.malinki.pz.lib.entity.FlightUVM;

public class FlightConverter {

    public static FlightToSearchDTO fromFlightToSearchUVMToFlightToSearchDTO(FlightToSearchUVM flightToSearchUVM) {
        if(flightToSearchUVM == null)
            return null;
        else
            return new FlightToSearchDTO.FlightDTOBuilder()
                    .dateStart(flightToSearchUVM.getDateStart())
                    .dateEnd(flightToSearchUVM.getDateEnd())
                    .from(flightToSearchUVM.getFrom())
                    .to(flightToSearchUVM.getTo())
                    ._class(flightToSearchUVM.get_class())
                    .numberOfPassengers(flightToSearchUVM.getNumberOfPassengers())
                    .build();
    }

    public static FlightToSearchUVM fromFlightToSearchDTOToFlightToSearchUVM(FlightToSearchDTO flightToSearchDTO) {
        if(flightToSearchDTO == null)
            return null;
        else
            return new FlightToSearchUVM.FlightUVMBuilder()
                    .dateStart(flightToSearchDTO.getDateStart())
                    .dateEnd(flightToSearchDTO.getDateEnd())
                    .from(flightToSearchDTO.getFrom())
                    .to(flightToSearchDTO.getTo())
                    ._class(flightToSearchDTO.get_class())
                    .numberOfPassengers(flightToSearchDTO.getNumberOfPassengers())
                    .build();
    }

    ///////

    public static FlightDTO fromFlightUVMToFlightDTO(FlightUVM flightUVM) {
        if(flightUVM == null)
            return null;
        else
            return new FlightDTO.FlightDTOBuilder()
                    .flightNumber(flightUVM.getFlightNumber())
                    .departureDate(flightUVM.getDepartureDate())
                    .arrivalDate(flightUVM.getArrivalDate())
                    .basePrice(flightUVM.getPrice())
                    .airlineName(flightUVM.getAirlineName())
                    .airlineShortcut(flightUVM.getAirlineShortcut())
                    .from(flightUVM.getFrom())
                    .to(flightUVM.getTo())
                    ._class(flightUVM.get_class())
                    .numberOfPlaces(flightUVM.getNumberOfPlaces())
                    .build();
    }

    public static FlightUVM fromFlightDTOToFlightUVM(FlightDTO flightDTO) {
        if(flightDTO == null)
            return null;
        else
            return new FlightUVM.FlightUVMBuilder()
                    .flightNumber(flightDTO.getFlightNumber())
                    .departureDate(flightDTO.getDepartureDate())
                    .arrivalDate(flightDTO.getArrivalDate())
                    .basePrice(flightDTO.getPrice())
                    .airlineName(flightDTO.getAirlineName())
                    .airlineShortcut(flightDTO.getAirlineShortcut())
                    .from(flightDTO.getFrom())
                    .to(flightDTO.getTo())
                    ._class(flightDTO.get_class())
                    .numberOfPlaces(flightDTO.getNumberOfPlaces())
                    .build();
    }
}
