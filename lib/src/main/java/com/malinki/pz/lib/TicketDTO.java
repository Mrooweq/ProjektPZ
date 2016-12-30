package com.malinki.pz.lib;

import com.malinki.pz.lib.TicketUVM.TicketDTOTicketDTOBuilder;

public class TicketDTO {
	
	private String sourceAirport;
	private String destinyAirport;
	private String flightDate;
	private String airlineName;
	private String firstname;
	private String lastName;
	private String nrIDCard;
	private String email;
	
    private String flight;
    private String flightClass;
    private String user;
    private double priceMultiplier;

    public TicketDTO(){}

    private TicketDTO(TicketDTO.TicketDTOBuilder builder) {
        this.flight = builder.flight;
        this.flightClass = builder.flightClass;
        this.user = builder.user;
        
        this.sourceAirport = builder.sourceAirport;
        this.destinyAirport = builder.destinyAirport;
        this.flightDate = builder.flightDate;
        this.airlineName = builder.airlineName;
        this.firstname = builder.firstname;
        this.lastName = builder.lastName;
        this.nrIDCard = builder.nrIDCard;
        this.email = builder.email;
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

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }


    public String getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getDestinyAirport() {
		return destinyAirport;
	}

	public void setDestinyAirport(String destinyAirport) {
		this.destinyAirport = destinyAirport;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNrIDCard() {
		return nrIDCard;
	}

	public void setNrIDCard(String nrIDCard) {
		this.nrIDCard = nrIDCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public static class TicketDTOBuilder {
        private String flight;
        private String flightClass;
        private String user;
        
        private String sourceAirport;
    	private String destinyAirport;
    	private String flightDate;
    	private String airlineName;
    	private String firstname;
    	private String lastName;
    	private String nrIDCard;
    	private String email;

        public TicketDTO.TicketDTOBuilder flight(String flight) {
            this.flight = flight;
            return this;
        }

        public TicketDTO.TicketDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketDTO.TicketDTOBuilder user(String user) {
            this.user = user;
            return this;
        }
        
        
        public TicketDTO.TicketDTOBuilder sourceAirport(String sourceAirport) {
            this.sourceAirport = sourceAirport;
            return this;
        }

        public TicketDTO.TicketDTOBuilder destinyAirport(String destinyAirport) {
            this.destinyAirport = destinyAirport;
            return this;
        }

        public TicketDTO.TicketDTOBuilder flightDate(String flightDate) {
            this.flightDate = flightDate;
            return this;
        }

        public TicketDTO.TicketDTOBuilder airlineName(String airlineName) {
            this.airlineName = airlineName;
            return this;
        }

        public TicketDTO.TicketDTOBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public TicketDTO.TicketDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public TicketDTO.TicketDTOBuilder nrIDCard(String nrIDCard) {
            this.nrIDCard = nrIDCard;
            return this;
        }
        
        public TicketDTO.TicketDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public TicketDTO build() {
            return new TicketDTO(this);
        }
    }
}
