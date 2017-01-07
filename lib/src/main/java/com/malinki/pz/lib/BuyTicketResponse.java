package com.malinki.pz.lib;

public class BuyTicketResponse {
    private FlightUVM flight;
    private UserUVM user;
    private FlightClass flightClass;

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

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }
}
