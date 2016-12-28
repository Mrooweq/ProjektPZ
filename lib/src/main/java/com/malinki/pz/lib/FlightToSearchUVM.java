package com.malinki.pz.lib;


public class FlightToSearchUVM {
    private String dateStart;
    private String dateEnd;
    private String from;
    private String to;

    public FlightToSearchUVM(){}

    private FlightToSearchUVM(FlightToSearchUVM.FlightUVMBuilder builder) {
        this.dateStart = builder.dateStart;
        this.dateEnd = builder.dateEnd;
        this.from = builder.from;
        this.to = builder.to;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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

    public static class FlightUVMBuilder {
        private String dateStart;
        private String dateEnd;
        private String from;
        private String to;

        public FlightToSearchUVM.FlightUVMBuilder dateStart(String dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        public FlightToSearchUVM.FlightUVMBuilder dateEnd(String dateEnd) {
            this.dateEnd = dateEnd;
            return this;
        }

        public FlightToSearchUVM.FlightUVMBuilder from(String from) {
            this.from = from;
            return this;
        }

        public FlightToSearchUVM.FlightUVMBuilder to(String to) {
            this.to = to;
            return this;
        }

        public FlightToSearchUVM build() {
            return new FlightToSearchUVM(this);
        }
    }
}




