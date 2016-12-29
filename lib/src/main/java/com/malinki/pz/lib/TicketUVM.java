package com.malinki.pz.lib;


public class TicketUVM {
    private String flight;
    private String flightClass;
    private String username;

    private TicketUVM(TicketUVM.TicketUVMBuilder builder) {
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

    public static class TicketUVMBuilder {
        private String flight;
        private String flightClass;
        private String username;

        public TicketUVM.TicketUVMBuilder flight(String flight) {
            this.flight = flight;
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

        public TicketUVM build() {
            return new TicketUVM(this);
        }
    }
}
