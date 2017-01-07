package com.malinki.pz.lib;


public class TicketUVM {
    private int flightNumber;
    private String flightClass;
    private String username;
    private String airlineShortcut;
    private int numberOfPlaces;

    private TicketUVM(TicketUVM.TicketUVMBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.flightClass = builder.flightClass;
        this.username = builder.username;
        this.airlineShortcut = builder.airlineShortcut;
        this.numberOfPlaces = builder.numberOfPlaces;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAirlineShortcut() {
        return airlineShortcut;
    }

    public void setAirlineShortcut(String airlineShortcut) {
        this.airlineShortcut = airlineShortcut;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public static class TicketUVMBuilder {
        private int flightNumber;
        private String flightClass;
        private String username;
        private String airlineShortcut;
        private int numberOfPlaces;

        public TicketUVM.TicketUVMBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketUVM.TicketUVMBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketUVM.TicketUVMBuilder username(String user) {
            this.username = user;
            return this;
        }

        public TicketUVM.TicketUVMBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketUVM.TicketUVMBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public TicketUVM build() {
            return new TicketUVM(this);
        }
    }
}
