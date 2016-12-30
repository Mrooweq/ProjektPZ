package com.malinki.pz.lib;


public class TicketUVM {
	
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

    public TicketUVM(){}

    private TicketUVM(TicketUVM.TicketDTOTicketDTOBuilder builder) {
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

    public String getSuroceAirport() {
		return sourceAirport;
	}

	public void sourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getDestinyAirport() {
		return destinyAirport;
	}

	public void setDestinyAirport(String destinyAirport) {
		this.destinyAirport = destinyAirport;
	}

	public String getFlyDate() {
		return flightDate;
	}

	public void setFlyDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getName() {
		return firstname;
	}

	public void setName(String firstname) {
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

	public static class TicketDTOTicketDTOBuilder {
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

        public TicketDTOTicketDTOBuilder flight(String flight) {
            this.flight = flight;
            return this;
        }

        public TicketDTOTicketDTOBuilder flightClass(String flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public TicketDTOTicketDTOBuilder user(String user) {
            this.user = user;
            return this;
        }
        
        public TicketDTOTicketDTOBuilder sourceAirport(String sourceAirport) {
            this.sourceAirport = sourceAirport;
            return this;
        }

        public TicketDTOTicketDTOBuilder destinyAirport(String destinyAirport) {
            this.destinyAirport = destinyAirport;
            return this;
        }

        public TicketDTOTicketDTOBuilder flightDate(String flightDate) {
            this.flightDate = flightDate;
            return this;
        }

        public TicketDTOTicketDTOBuilder airlineName(String airlineName) {
            this.airlineName = airlineName;
            return this;
        }

        public TicketDTOTicketDTOBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public TicketDTOTicketDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public TicketDTOTicketDTOBuilder nrIDCard(String nrIDCard) {
            this.nrIDCard = nrIDCard;
            return this;
        }
        
        public TicketDTOTicketDTOBuilder email(String email) {
            this.email = email;
            return this;
        }


        public TicketUVM build() {
            return new TicketUVM(this);
        }
    }
}
