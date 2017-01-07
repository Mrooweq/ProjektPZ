package com.malinki.pz.lib;


public class TicketDTO {
    private int flightNumber;
    private String flightClass;
    private String username;
    private String airlineShortcut;
    private int numberOfPlaces;

    private TicketDTO(TicketDTO.TicketDTOBuilder builder) {
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

    public static class TicketDTOBuilder {
        private int flightNumber;
        private String flightClass;
        private String username;
        private String airlineShortcut;
        private int numberOfPlaces;

        public TicketDTO.TicketDTOBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketDTO.TicketDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketDTO.TicketDTOBuilder username(String user) {
            this.username = user;
            return this;
        }

        public TicketDTO.TicketDTOBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketDTO.TicketDTOBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public TicketDTO build() {
            return new TicketDTO(this);
        }
    }
}
