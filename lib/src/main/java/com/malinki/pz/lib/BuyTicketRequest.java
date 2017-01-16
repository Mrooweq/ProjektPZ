package com.malinki.pz.lib;

public class BuyTicketRequest {
    private FlightUVM flight;
    private UserUVM user;

    public FlightUVM getFlight() {
        return flight;
    }

    public void setFlight(FlightUVM flight) {
        this.flight = flight;
    }

    public UserUVM getUser() {
        return user;
    }

    public void setUser(UserUVM user) {
        this.user = user;
    }
}
