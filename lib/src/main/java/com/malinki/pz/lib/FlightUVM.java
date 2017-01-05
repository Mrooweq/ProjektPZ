package com.malinki.pz.lib;


public class FlightUVM {
    private int flightNumber;
    private String departureDate;
    private String arrivalDate;
    private int price;
    private String airlineName;
    private String airlineShortcut;
    private String from;
    private String to;
    private int freePlaces;

    public FlightUVM(){}

    private FlightUVM(FlightUVM.FlightUVMBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.departureDate = builder.departureDate;
        this.arrivalDate = builder.arrivalDate;
        this.price = builder.price;
        this.airlineName = builder.airlineName;
        this.from = builder.from;
        this.to = builder.to;
        this.freePlaces = builder.freePlaces;
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

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public String getAirlineShortcut() {
        return airlineShortcut;
    }

    public void setAirlineShortcut(String airlineShortcut) {
        this.airlineShortcut = airlineShortcut;
    }

    public static class FlightUVMBuilder {
        private int flightNumber;
        private String departureDate;
        private String arrivalDate;
        private int price;
        private String airlineName;
        private String from;
        private String to;
        private int freePlaces;
        private String airlineShortcut;

        public FlightUVM.FlightUVMBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightUVM.FlightUVMBuilder departureDate(String departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public FlightUVM.FlightUVMBuilder arrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public FlightUVM.FlightUVMBuilder basePrice(int basePrice) {
            this.price = basePrice;
            return this;
        }

        public FlightUVM.FlightUVMBuilder airlineName(String airlineName) {
            this.airlineName = airlineName;
            return this;
        }

        public FlightUVM.FlightUVMBuilder from(String from) {
            this.from = from;
            return this;
        }

        public FlightUVM.FlightUVMBuilder freePlaces(int freePlaces) {
            this.freePlaces = freePlaces;
            return this;
        }

        public FlightUVM.FlightUVMBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public FlightUVM.FlightUVMBuilder to(String to) {
            this.to = to;
            return this;
        }

        public FlightUVM build() {
            return new FlightUVM(this);
        }
    }
}




