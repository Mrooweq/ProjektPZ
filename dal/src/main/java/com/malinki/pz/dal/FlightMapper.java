package com.malinki.pz.dal;

import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightToSearchDTO;
import com.malinki.pz.lib.TicketDTO;
import com.malinki.pz.lib.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT Flight_Number AS flightNumber, departure_date AS departureDate, arrival_date AS arrivalDate, (base_price * Multiplier.Multiplier) AS price, Airline.name AS airlineName, " +
            "src.Name AS \"From\", dest.Name AS \"To\", free_places AS freePlaces, Airline.name_Shortcut AS airlineShortcut " +
            "FROM Flight, Airline, Airport src, Airport dest, Multiplier, \"CLASS\" " +
            "WHERE Flight.ID_Airline = Airline.ID_Airline AND Flight.ID_Airline = Airline.ID_Airline " +
            "AND src.ID_AIRPORT = \"From\" AND dest.ID_AIRPORT = \"To\" " +
            "AND src.NAME = #{from} AND dest.NAME = #{to} " +
            "AND Departure_Date BETWEEN #{dateStart} AND #{dateEnd} " +
            "and Multiplier.ID_Multiplier = \"CLASS\".ID_Multiplier and \"CLASS\".Name = #{_class} " +
            "AND free_places >= #{numberOfPassengers}")
    ArrayList<FlightDTO> getFlights(FlightToSearchDTO flightToSearchDTO);


    @Select("SELECT Name FROM Class")
    ArrayList<String> getClasses();

    @Select("INSERT INTO Ticket VALUES (" +
            "getMinTicketID, " +
            "(SELECT ID_Flight FROM Flight, Airline WHERE FLIGHT_NUMBER = #{flightNumber} AND Airline.NAME_SHORTCUT = #{airlineShortcut}), " +
            "(SELECT ID_Class FROM Class WHERE Name = #{flightClass}), " +
            "(SELECT ID_User FROM \"User\" WHERE Username = #{username}))")
    void addTicket(TicketDTO ticket);

    @Select("COMMIT")
    void commit();
}