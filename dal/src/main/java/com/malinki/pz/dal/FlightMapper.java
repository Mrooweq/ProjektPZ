package com.malinki.pz.dal;

import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightToSearchDTO;
import com.malinki.pz.lib.TicketRequestDTO;
import com.malinki.pz.lib.TicketResponseDTO;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.util.ArrayList;
import java.util.List;

public interface FlightMapper {
    @Select("SELECT DISTINCT dest.Name FROM Flight, Airport src, Airport dest WHERE \"From\"=src.ID_Airport AND \"To\"=dest.ID_Airport")
    ArrayList<String> getPossibleDestinations();

    @Select("SELECT DISTINCT dest.Name FROM Flight, Airport src, Airport dest WHERE \"From\"=src.ID_Airport AND \"To\"=dest.ID_Airport AND src.Name=#{src}")
    ArrayList<String> getPossibleDestinationsWithParam(@Param("src") String src);

    @Select("SELECT DISTINCT src.Name FROM Flight, Airport src, Airport dest WHERE \"From\"=src.ID_Airport AND \"To\"=dest.ID_Airport")
    ArrayList<String> getPossibleSources();

    @Select("SELECT DISTINCT src.Name FROM Flight, Airport src, Airport dest WHERE \"From\"=src.ID_Airport AND \"To\"=dest.ID_Airport AND dest.Name=#{dest}")
    ArrayList<String> getPossibleSourcesWithParam(@Param("dest") String dest);

    @Lang(XMLLanguageDriver.class)
    @Select("<script>" +
            "SELECT Flight_Number AS flightNumber, departure_date AS departureDate, arrival_date AS arrivalDate, (base_price * Multiplier.Multiplier) AS price, " +
            "Airline.name AS airlineName, src.Name AS \"From\", dest.Name AS \"To\", free_places AS freePlaces, Airline.name_Shortcut AS airlineShortcut FROM Flight, " +
            "Airline, Airport src, Airport dest, Multiplier, \"CLASS\" " +
            "WHERE " +
            "Flight.ID_Airline = Airline.ID_Airline AND Flight.ID_Airline = Airline.ID_Airline " +
            "AND src.ID_AIRPORT = \"From\" AND dest.ID_AIRPORT = \"To\" " +
            "AND Multiplier.ID_Multiplier = \"CLASS\".ID_Multiplier" +
            " <if test=\"from != null\">" +
            "    AND src.NAME = #{from}" +
            " </if>\n" +
            " <if test=\"to != null\">" +
            "    AND dest.NAME = #{to}" +
            " </if>" +
            "<if test=\"dateStart != null and dateEnd != null\">" +
            "    AND TO_CHAR (Departure_Date, 'YYYY-MM-DD') BETWEEN #{dateStart} AND #{dateEnd} " +
            " </if>" +
            "<if test=\"_class != null\">" +
            "   AND \"CLASS\".Name = #{_class}" +
            "</if>" +
            "<if test=\"numberOfPassengers != null\">" +
            "   AND free_places >= #{numberOfPassengers}" +
            "</if>" +
            "</script>")
    ArrayList<FlightDTO> getFlights(FlightToSearchDTO flightToSearchDTO);

    @Select("SELECT Name FROM Class")
    ArrayList<String> getClasses();

    @Select("INSERT INTO Ticket VALUES (" +
            "seq_Ticket.NEXTVAL, " +
            "#{numberOfPlaces}, " +
            "(SELECT ID_Flight FROM Flight, Airline WHERE FLIGHT_NUMBER = #{flightNumber} AND Airline.NAME_SHORTCUT = #{airlineShortcut}), " +
            "(SELECT ID_Class FROM Class WHERE Name = #{flightClass}), " +
            "(SELECT ID_User FROM \"User\" WHERE Username = #{username}))")
    void addTicket(TicketRequestDTO ticket);

    @Select("select Name_Shortcut as airlineShortcut, Flight_number as flightNumber, departure_date as departureDate, arrival_date as arrivalDate, " +
            "(base_price * multiplier) as price, Number_Of_Places as numberOfPlaces, Class.NAME as flightClass, " +
            "Airline.Name as airline, src.Name as \"from\", dest.Name as \"to\" " +
            "from Ticket, Flight, Class, \"User\", Airline, Multiplier, Airport src, Airport dest " +
            "where Ticket.ID_FLIGHT = Flight.ID_FLIGHT " +
            "and Ticket.ID_CLASS = Class.ID_CLASS " +
            "and Ticket.ID_USER = \"User\".ID_USER " +
            "and Flight.ID_AIRLINE = Airline.ID_AIRLINE " +
            "and Multiplier.ID_MULTIPLIER = Class.ID_MULTIPLIER " +
            "and Flight.\"From\" = src.ID_AIRPORT " +
            "and Flight.\"To\" = dest.ID_AIRPORT " +
            "and Username = #{username} ")
    List <TicketResponseDTO> getArchivalTickets(String username);







    @Select("COMMIT")
    void commit();
}