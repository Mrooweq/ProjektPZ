package com.malinki.pz.lib;


public class TicketDTO {
    private String flight;
    private String flightClass;
    private String username;

    private TicketDTO(TicketDTO.TicketDTOBuilder builder) {
        this.flight = builder.flight;
        this.flightClass = builder.flightClass;
        this.username = builder.username;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
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

    public static class TicketDTOBuilder {
        private String flight;
        private String flightClass;
        private String username;

        public TicketDTO.TicketDTOBuilder flight(String flight) {
            this.flight = flight;
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

        public TicketDTO build() {
            return new TicketDTO(this);
        }
    }
}
