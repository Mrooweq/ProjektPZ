package com.malinki.pz.lib;


public class FlightDTO {
    private int flightNumber;
    private String departureDate;
    private String arrivalDate;
    private int price;
    private String airlineName;
    private String airlineShortcut;
    private String from;
    private String to;
    private int numberOfPlaces;

    public FlightDTO(){}

    private FlightDTO(FlightDTO.FlightDTOBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.departureDate = builder.departureDate;
        this.arrivalDate = builder.arrivalDate;
        this.price = builder.price;
        this.airlineName = builder.airlineName;
        this.from = builder.from;
        this.to = builder.to;
        this.numberOfPlaces = builder.numberOfPlaces;
        this.airlineShortcut = builder.airlineShortcut;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getAirlineShortcut() {
        return airlineShortcut;
    }

    public void setAirlineShortcut(String airlineShortcut) {
        this.airlineShortcut = airlineShortcut;
    }

    public static class FlightDTOBuilder {
        private int flightNumber;
        private String departureDate;
        private String arrivalDate;
        private int price;
        private String airlineName;
        private String from;
        private String to;
        private int numberOfPlaces;
        private String airlineShortcut;

        public FlightDTO.FlightDTOBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightDTO.FlightDTOBuilder departureDate(String departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public FlightDTO.FlightDTOBuilder arrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public FlightDTO.FlightDTOBuilder basePrice(int basePrice) {
            this.price = basePrice;
            return this;
        }

        public FlightDTO.FlightDTOBuilder airlineName(String airlineName) {
            this.airlineName = airlineName;
            return this;
        }

        public FlightDTO.FlightDTOBuilder from(String from) {
            this.from = from;
            return this;
        }

        public FlightDTO.FlightDTOBuilder numberOfPlaces(int freePlaces) {
            this.numberOfPlaces = freePlaces;
            return this;
        }

        public FlightDTO.FlightDTOBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
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




