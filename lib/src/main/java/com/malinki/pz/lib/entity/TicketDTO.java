package com.malinki.pz.lib.entity;

public class TicketDTO {
	
    private String flight;
    private String flightClass;
    private String user;
    private double priceMultiplier;

    public TicketDTO(){}

    private TicketDTO(TicketDTO.TicketDTOBuilder builder) {
        this.flight = builder.flight;
        this.flightClass = builder.flightClass;
        this.user = builder.user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

	public static class TicketDTOBuilder {
        private String flight;
        private String flightClass;
        private String user;

        public TicketDTO.TicketDTOBuilder flight(String flight) {
            this.flight = flight;
            return this;
        }

        public TicketDTO.TicketDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketDTO.TicketDTOBuilder user(String user) {
            this.user = user;
            return this;
        }

        public TicketDTO build() {
            return new TicketDTO(this);
        }
    }
}
