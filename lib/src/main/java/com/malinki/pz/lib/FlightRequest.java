package com.malinki.pz.lib;

import java.util.List;

public class FlightRequest {

    private FlightToSearchDTO flightToSearchDTO;
    private FlightToSearchUVM flightToSearchUVM;

    public FlightToSearchDTO getFlightToSearchDTO() {
        return flightToSearchDTO;
    }

    public void setFlightToSearchDTO(FlightToSearchDTO flightToSearchDTO) {
        this.flightToSearchDTO = flightToSearchDTO;
    }

    public FlightToSearchUVM getFlightToSearchUVM() {
        return flightToSearchUVM;
    }

    public void setFlightToSearchUVM(FlightToSearchUVM flightToSearchUVM) {
        this.flightToSearchUVM = flightToSearchUVM;
    }
}
