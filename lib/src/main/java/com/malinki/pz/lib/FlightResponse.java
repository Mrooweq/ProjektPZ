package com.malinki.pz.lib;

import java.util.List;

public class FlightResponse {
    private List<FlightDTO> flightDTOResultList;
    private List<FlightUVM> flightUVMResultList;
    private int result;

    public List<FlightDTO> getFlightDTOResultList() {
        return flightDTOResultList;
    }

    public void setFlightDTOResultList(List<FlightDTO> flightDTOResultList) {
        this.flightDTOResultList = flightDTOResultList;
    }

    public List<FlightUVM> getFlightUVMResultList() {
        return flightUVMResultList;
    }

    public void setFlightUVMResultList(List<FlightUVM> flightUVMResultList) {
        this.flightUVMResultList = flightUVMResultList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
