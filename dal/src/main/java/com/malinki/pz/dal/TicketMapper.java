package com.malinki.pz.dal;

import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightToSearchDTO;
import com.malinki.pz.lib.TicketRequestDTO;
import com.malinki.pz.lib.TicketResponseDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.util.ArrayList;
import java.util.List;

public interface TicketMapper extends Mapper {
    @Insert("INSERT INTO Ticket VALUES (" +
            "seq_Ticket.NEXTVAL, " +
            "#{numberOfPlaces}, " +
            "(SELECT ID_Flight FROM Flight, Airline WHERE FLIGHT_NUMBER = #{flightNumber} AND Airline.NAME_SHORTCUT = #{airlineShortcut}), " +
            "(SELECT ID_Class FROM Class WHERE Name = #{flightClass}), " +
            "(SELECT ID_User FROM \"User\" WHERE Username = #{username}))")
    @SelectKey(statement="select seq_Ticket.CURRVAL from Dual", keyProperty="id", before=false, resultType=int.class)
    int addTicket(TicketRequestDTO ticket);

    @Select("select Name_Shortcut as airlineShortcut, Flight_number as flightNumber, departure_date as departureDate, arrival_date as arrivalDate, " +
            "(base_price * multiplier * Number_Of_Places) as price, Number_Of_Places as numberOfPlaces, Class.NAME as flightClass, Airline.Name as airline, src.Name as \"from\", dest.Name as \"to\" " +
            "from Ticket, Flight, Class, \"User\", Airline, Multiplier, Airport src, Airport dest " +
            "where Ticket.ID_FLIGHT = Flight.ID_FLIGHT " +
            "and Ticket.ID_CLASS = Class.ID_CLASS  " +
            "and Ticket.ID_USER = \"User\".ID_USER " +
            "and Flight.ID_AIRLINE = Airline.ID_AIRLINE " +
            "and Multiplier.ID_MULTIPLIER = Class.ID_MULTIPLIER " +
            "and Flight.\"From\" = src.ID_AIRPORT " +
            "and Flight.\"To\" = dest.ID_AIRPORT " +
            "and Username = #{username}")
    List <TicketResponseDTO> getArchivalTickets(String username);

    @Select("    select ID_Ticket as id, Airline.Name as airline, Name_Shortcut as airlineShortcut, Flight_number as flightNumber,\n" +
            "    src.Name as \"from\", dest.Name as \"to\",\n" +
            "    departure_date as departureDate, arrival_date as arrivalDate,\n" +
            "    (base_price * multiplier * Number_Of_Places) as price, Number_Of_Places as numberOfPlaces, Class.NAME as flightClass, firstname, lastname, email\n" +
            "    from Ticket, Flight, Class, \"User\", Airline, Multiplier, Airport src, Airport dest\n" +
            "    where Ticket.ID_FLIGHT = Flight.ID_FLIGHT\n" +
            "    and Ticket.ID_CLASS = Class.ID_CLASS\n" +
            "    and Ticket.ID_USER = \"User\".ID_USER\n" +
            "    and Flight.ID_AIRLINE = Airline.ID_AIRLINE\n" +
            "    and Multiplier.ID_MULTIPLIER = Class.ID_MULTIPLIER\n" +
            "    and Flight.\"From\" = src.ID_AIRPORT\n" +
            "    and Flight.\"To\" = dest.ID_AIRPORT\n" +
            "    and ID_Ticket = #{id}")
    TicketResponseDTO getTicketByID(int id);
}