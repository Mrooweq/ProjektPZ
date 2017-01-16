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

public interface FlightMapper extends Mapper {
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
            "SELECT Flight_Number AS flightNumber, departure_date AS departureDate, arrival_date AS arrivalDate, (base_price * Multiplier.Multiplier * #{numberOfPassengers}) AS price, " +
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
}