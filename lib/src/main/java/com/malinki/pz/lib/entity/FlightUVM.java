package com.malinki.pz.lib.entity;


public class FlightUVM {
    private int flightNumber;
    private String departureDate;
    private String arrivalDate;
    private int price;
    private String airlineName;
    private String airlineShortcut;
    private String from;
    private String to;
    private String _class;
    private int numberOfPlaces;

    public FlightUVM(){}

    private FlightUVM(FlightUVM.FlightUVMBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.departureDate = builder.departureDate;
        this.arrivalDate = builder.arrivalDate;
        this.price = builder.price;
        this.airlineName = builder.airlineName;
        this.from = builder.from;
        this.to = builder.to;
        this.airlineShortcut = builder.airlineShortcut;
        this._class = builder._class;
        this.numberOfPlaces = builder.numberOfPlaces;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
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
        private String airlineShortcut;
        private String _class;
        private int numberOfPlaces;

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

        public FlightUVM.FlightUVMBuilder to(String to) {
            this.to = to;
            return this;
        }

        public FlightUVM.FlightUVMBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public FlightUVM.FlightUVMBuilder _class(String _class) {
            this._class = _class;
            return this;
        }

        public FlightUVM.FlightUVMBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public FlightUVM build() {
            return new FlightUVM(this);
        }
    }
}




