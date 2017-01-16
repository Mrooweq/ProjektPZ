package com.malinki.pz.lib;


public class TicketResponseDTO {
    private int id;
    private String airline;
    private String airlineShortcut;
    private int flightNumber;
    private String from;
    private String to;
    private String departureDate;
    private String arrivalDate;
    private int price;
    private int numberOfPlaces;
    private String flightClass;
    private String firstname;
    private String lastname;
    private String email;

    public TicketResponseDTO(){}

    private TicketResponseDTO(TicketResponseDTO.TicketResponseDTOBuilder builder) {
        this.id = builder.id;
        this.airline = builder.airline;
        this.airlineShortcut = builder.airlineShortcut;
        this.flightNumber = builder.flightNumber;
        this.from = builder.from;
        this.to = builder.to;
        this.departureDate = builder.departureDate;
        this.arrivalDate = builder.arrivalDate;
        this.price = builder.price;
        this.numberOfPlaces = builder.numberOfPlaces;
        this.flightClass = builder.flightClass;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
    }

    public static class TicketResponseDTOBuilder {
        private int id;
        private String airline;
        private String airlineShortcut;
        private int flightNumber;
        private String from;
        private String to;
        private String departureDate;
        private String arrivalDate;
        private int price;
        private int numberOfPlaces;
        private String flightClass;
        private String firstname;
        private String lastname;
        private String email;

        public TicketResponseDTO.TicketResponseDTOBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder departureDate(String departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder arrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder price(int price) {
            this.price = price;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder numberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder airline(String airline) {
            this.airline = airline;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder from(String from) {
            this.from = from;
            return this;
        }

        public TicketResponseDTO.TicketResponseDTOBuilder to(String to) {
            this.to = to;
            return this;
        }

        public TicketResponseDTO build() {
            return new TicketResponseDTO(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
