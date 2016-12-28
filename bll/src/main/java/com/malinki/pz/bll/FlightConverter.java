package com.malinki.pz.bll;

import com.malinki.pz.lib.*;

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
                    .build();
    }

    ///////

    public static FlightDTO fromFlightUVMToFlightDTO(FlightUVM flightUVM) {
        if(flightUVM == null)
            return null;
        else
            return new FlightDTO.FlightDTOBuilder()
                    .flightNumber(flightUVM.getFlightNumber())
                    .flightDate(flightUVM.getFlightDate())
                    .basePrice(flightUVM.getBasePrice())
                    .airline(flightUVM.getAirline())
                    .from(flightUVM.getFrom())
                    .to(flightUVM.getTo())
                    .build();
    }

    public static FlightUVM fromFlightDTOToFlightUVM(FlightDTO flightDTO) {
        if(flightDTO == null)
            return null;
        else
            return new FlightUVM.FlightUVMBuilder()
                    .flightNumber(flightDTO.getFlightNumber())
                    .flightDate(flightDTO.getFlightDate())
                    .basePrice(flightDTO.getBasePrice())
                    .airline(flightDTO.getAirline())
                    .from(flightDTO.getFrom())
                    .to(flightDTO.getTo())
                    .build();
    }
}
