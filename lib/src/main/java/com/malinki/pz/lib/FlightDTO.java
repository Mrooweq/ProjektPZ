package com.malinki.pz.lib;


public class FlightDTO {
    private int flightNumber;
    private String flightDate;
    private int basePrice;
    private String airline;
    private String from;
    private String to;

    public FlightDTO(){}

    private FlightDTO(FlightDTO.FlightDTOBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.basePrice = builder.basePrice;
        this.airline = builder.airline;
        this.from = builder.from;
        this.to = builder.to;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static class FlightDTOBuilder {
        private int flightNumber;
        private String flightDate;
        private int basePrice;
        private String airline;
        private String from;
        private String to;

        public FlightDTO.FlightDTOBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightDTO.FlightDTOBuilder flightDate(String flightDate) {
            this.flightDate = flightDate;
            return this;
        }

        public FlightDTO.FlightDTOBuilder basePrice(int basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public FlightDTO.FlightDTOBuilder airline(String airline) {
            this.airline = airline;
            return this;
        }

        public FlightDTO.FlightDTOBuilder from(String from) {
            this.from = from;
            return this;
        }

        public FlightDTO.FlightDTOBuilder to(String to) {
            this.to = to;
            return this;
        }

        public FlightDTO build() {
            return new FlightDTO(this);
        }
    }
}




