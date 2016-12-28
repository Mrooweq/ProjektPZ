package com.malinki.pz.lib;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class PossibleAirportsResponse {
    private List<String> possibleAirportsList;
    private int result;

    public PossibleAirportsResponse() {
        this.result = HttpServletResponse.SC_BAD_REQUEST;
    }

    public List<String> getPossibleAirportsList() {
        return possibleAirportsList;
    }

    public void setPossibleAirportsList(List<String> possibleAirportsList) {
        this.possibleAirportsList = possibleAirportsList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
