package com.malinki.pz.lib;


public class TicketUVM {
    private String flight;
    private String flightClass;
    private String user;

    private int priceMultiplier;

    public TicketUVM(){}

    private TicketUVM(TicketUVM.TicketUVMBuilder builder) {
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

    public int getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(int priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public static class TicketUVMBuilder {
        private String flight;
        private String flightClass;
        private String user;

        public TicketUVMBuilder flight(String flight) {
            this.flight = flight;
            return this;
        }

        public TicketUVMBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketUVMBuilder user(String user) {
            this.user = user;
            return this;
        }

        public TicketUVM build() {
            return new TicketUVM(this);
        }
    }
}
