package com.malinki.pz.lib;


public class TicketUVM {
    private String flightNumber;
    private String flightClass;
    private String username;
    private String airlineShortcut;

    private TicketUVM(TicketUVM.TicketUVMBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.flightClass = builder.flightClass;
        this.username = builder.username;
        this.airlineShortcut = builder.airlineShortcut;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
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

    public static class TicketUVMBuilder {
        private String flightNumber;
        private String flightClass;
        private String username;
        private String airlineShortcut;

        public TicketUVM.TicketUVMBuilder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public TicketUVM.TicketUVMBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketUVM.TicketUVMBuilder username(String user) {
            this.username = user;
            return this;
        }

        public TicketUVM.TicketUVMBuilder airlineShortcut(String airlineShortcut) {
            this.airlineShortcut = airlineShortcut;
            return this;
        }

        public TicketUVM build() {
            return new TicketUVM(this);
        }
    }
}
