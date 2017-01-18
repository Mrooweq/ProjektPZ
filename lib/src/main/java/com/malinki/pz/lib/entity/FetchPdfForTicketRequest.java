package com.malinki.pz.lib.entity;


public class FetchPdfForTicketRequest {
    private TicketResponseUVM ticket;
    private String username;

    public FetchPdfForTicketRequest(){}

    public TicketResponseUVM getTicket() {
        return ticket;
    }

    public void setTicket(TicketResponseUVM ticket) {
        this.ticket = ticket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
