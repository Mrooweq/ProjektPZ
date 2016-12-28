package com.malinki.pz.lib;


public class FlightToSearchDTO {
    private String dateStart;
    private String dateEnd;
    private String from;
    private String to;

    public FlightToSearchDTO(){}

    private FlightToSearchDTO(FlightToSearchDTO.FlightDTOBuilder builder) {
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

    public static class FlightDTOBuilder {
        private String dateStart;
        private String dateEnd;
        private String from;
        private String to;

        public FlightToSearchDTO.FlightDTOBuilder dateStart(String dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        public FlightToSearchDTO.FlightDTOBuilder dateEnd(String dateEnd) {
            this.dateEnd = dateEnd;
            return this;
        }

        public FlightToSearchDTO.FlightDTOBuilder from(String from) {
            this.from = from;
            return this;
        }

        public FlightToSearchDTO.FlightDTOBuilder to(String to) {
            this.to = to;
            return this;
        }

        public FlightToSearchDTO build() {
            return new FlightToSearchDTO(this);
        }
    }
}




