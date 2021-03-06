package com.malinki.pz.bll.converters;

import com.malinki.pz.lib.entity.TicketRequestDTO;
import com.malinki.pz.lib.entity.TicketRequestUVM;
import com.malinki.pz.lib.entity.TicketResponseDTO;
import com.malinki.pz.lib.entity.TicketResponseUVM;

public class TicketConverter {

    public static TicketRequestDTO fromTicketRequestUVMToTicketRequestDTO(TicketRequestUVM ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketRequestDTO.TicketDTOBuilder()
                    .flightNumber(ticket.getFlightNumber())
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightClass(ticket.getFlightClass())
                    .username(ticket.getUsername())
                    .numberOfPlaces(ticket.getNumberOfPlaces())
                    .build();
    }

    public static TicketRequestUVM fromTicketRequestDTOToTicketRequestUVM(TicketRequestDTO ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketRequestUVM.TicketUVMBuilder()
                    .flightNumber(ticket.getFlightNumber())
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightClass(ticket.getFlightClass())
                    .username(ticket.getUsername())
                    .numberOfPlaces(ticket.getNumberOfPlaces())
                    .build();
    }

    ///////

    public static TicketResponseDTO fromTicketResponseUVMToTicketResponseDTO(TicketResponseUVM ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketResponseDTO.TicketResponseDTOBuilder()
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightNumber(ticket.getFlightNumber())
                    .departureDate(ticket.getDepartureDate())
                    .arrivalDate(ticket.getArrivalDate())
                    .price(ticket.getPrice())
                    .numberOfPlaces(ticket.getNumberOfPlaces())
                    .flightClass(ticket.getFlightClass())
                    .airline(ticket.getAirline())
                    .from(ticket.getFrom())
                    .to(ticket.getTo())
                    .id(ticket.getId())
                    .firstname(ticket.getFirstname())
                    .lastname(ticket.getLastname())
                    .email(ticket.getEmail())
                    .logoPath(ticket.getLogoPath())
                    .build();
    }

    public static TicketResponseUVM fromTicketResponseDTOToTicketResponseUVM(TicketResponseDTO ticket) {
        if(ticket == null)
            return null;
        else
            return new TicketResponseUVM.TicketResponseUVMBuilder()
                    .airlineShortcut(ticket.getAirlineShortcut())
                    .flightNumber(ticket.getFlightNumber())
                    .departureDate(ticket.getDepartureDate())
                    .arrivalDate(ticket.getArrivalDate())
                    .price(ticket.getPrice())
                    .numberOfPlaces(ticket.getNumberOfPlaces())
                    .flightClass(ticket.getFlightClass())
                    .airline(ticket.getAirline())
                    .from(ticket.getFrom())
                    .to(ticket.getTo())
                    .id(ticket.getId())
                    .firstname(ticket.getFirstname())
                    .lastname(ticket.getLastname())
                    .email(ticket.getEmail())
                    .logoPath(ticket.getLogoPath())
                    .build();
    }
}
