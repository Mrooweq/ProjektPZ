package com.malinki.pz.dal;

import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightToSearchDTO;
import com.malinki.pz.lib.TicketRequestDTO;
import com.malinki.pz.lib.TicketResponseDTO;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.util.ArrayList;
import java.util.List;

public interface TicketMapper extends Mapper {
    @Select("INSERT INTO Ticket VALUES (" +
            "seq_Ticket.NEXTVAL, " +
            "#{numberOfPlaces}, " +
            "(SELECT ID_Flight FROM Flight, Airline WHERE FLIGHT_NUMBER = #{flightNumber} AND Airline.NAME_SHORTCUT = #{airlineShortcut}), " +
            "(SELECT ID_Class FROM Class WHERE Name = #{flightClass}), " +
            "(SELECT ID_User FROM \"User\" WHERE Username = #{username}))")
    void addTicket(TicketRequestDTO ticket);

    @Select("select Name_Shortcut as airlineShortcut, Flight_number as flightNumber, departure_date as departureDate, arrival_date as arrivalDate,\n" +
            "(base_price * multiplier) as price, Number_Of_Places as numberOfPlaces, Class.NAME as flightClass, Airline.Name as airline, src.Name as \"from\", dest.Name as \"to\"\n" +
            "from Ticket, Flight, Class, \"User\", Airline, Multiplier, Airport src, Airport dest\n" +
            "where Ticket.ID_FLIGHT = Flight.ID_FLIGHT\n" +
            "and Ticket.ID_CLASS = Class.ID_CLASS \n" +
            "and Ticket.ID_USER = \"User\".ID_USER\n" +
            "and Flight.ID_AIRLINE = Airline.ID_AIRLINE\n" +
            "and Multiplier.ID_MULTIPLIER = Class.ID_MULTIPLIER\n" +
            "and Flight.\"From\" = src.ID_AIRPORT\n" +
            "and Flight.\"To\" = dest.ID_AIRPORT\n" +
            "and Username = #{username}\n")
    List <TicketResponseDTO> getArchivalTickets(String username);



    @Select("COMMIT")
    void commit();
}