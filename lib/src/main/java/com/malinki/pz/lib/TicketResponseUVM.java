package com.malinki.pz.lib;


public class TicketResponseUVM {
    private String airlineShortcut;
    private int flightNumber;
    private String departureDate;
    private String arrivalDate;
    private int price;
    private int numberOfPlaces;
    private String flightClass;
    private String airline;
    private String from;
    private String to;

    public TicketResponseUVM(){}

    private TicketResponseUVM(TicketResponseUVM.TicketResponseUVMBuilder builder) {
        this.airlineShortcut = builder.airlineShortcut;
        this.flightNumber = builder.flightNumber;
        this.departureDate = builder.departureDate;
        this.arrivalDate = builder.arrivalDate;
        this.price = builder.price;
        this.numberOfPlaces = builder.numberOfPlaces;
        this.flightClass = builder.flightClass;
        this.airline = builder.airline;
        this.from = builder.from;
        this.to = builder.to;
    }

    public String getAirlineShortcut() {
        return airlineShortcut;
    }

    public void setAirlineShortcut(String airlineShortcut) {
        this.airlineShortcut = airlineShortcut;
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

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
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

    public static class TicketResponseUVMBuilder {
        private String airlineShortcut;
        private int flightNumber;
        private String departureDate;
        private String arrivalDate;
        private int price;
        private int numberOfPlaces;
        private String flightClass;
        private String airline;
        private String from;
        private String to;

        public TicketResponseUVM.TicketResponseUVMBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder departureDate(String departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder arrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder price(int price) {
            this.price = price;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder airline(String airline) {
            this.airline = airline;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder from(String from) {
            this.from = from;
            return this;
        }

        public TicketResponseUVM.TicketResponseUVMBuilder to(String to) {
            this.to = to;
            return this;
        }

        public TicketResponseUVM build() {
            return new TicketResponseUVM(this);
        }
    }
}
