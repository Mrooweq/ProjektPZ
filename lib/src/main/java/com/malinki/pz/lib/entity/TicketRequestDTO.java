package com.malinki.pz.lib.entity;


public class TicketRequestDTO {
    private int id;
    private int flightNumber;
    private String flightClass;
    private String username;
    private String airlineShortcut;
    private int numberOfPlaces;

    private TicketRequestDTO(TicketRequestDTO.TicketDTOBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.flightClass = builder.flightClass;
        this.username = builder.username;
        this.airlineShortcut = builder.airlineShortcut;
        this.numberOfPlaces = builder.numberOfPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        public TicketRequestDTO.TicketDTOBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketRequestDTO.TicketDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketRequestDTO.TicketDTOBuilder username(String user) {
            this.username = user;
            return this;
        }

        public TicketRequestDTO.TicketDTOBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketRequestDTO.TicketDTOBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public TicketRequestDTO build() {
            return new TicketRequestDTO(this);
        }
    }
}
