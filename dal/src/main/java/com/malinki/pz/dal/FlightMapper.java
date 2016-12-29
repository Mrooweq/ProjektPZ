package com.malinki.pz.dal;

import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightToSearchDTO;
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

    @Select("select Flight_Number as flightNumber, flight_date as flightDate, base_price as basePrice, Airline.name as airline, src.Name as \"From\", dest.Name as \"To\" " +
            "from Flight, Airline, Airport src, Airport dest " +
            "where Flight.ID_AIRLINE = Airline.ID_AIRLINE and Flight.ID_AIRLINE = Airline.ID_AIRLINE " +
            "and src.ID_AIRPORT = \"From\" and dest.ID_AIRPORT = \"To\" " +
            "and src.NAME = #{from} and dest.NAME = #{to} " +
            "and FLIGHT_DATE between #{dateStart} and #{dateEnd}")
    ArrayList<FlightDTO> getFlights(FlightToSearchDTO flightToSearchDTO);


    @Select("COMMIT")
    void commit();

    //////////////////

    @Select("INSERT INTO TICKET VALUES (1, #{flight}, #{flightClass}, (select ID_User from \"User\" where Username = #{username}))")
    void addTicket(@Param("flight") String flight,
                   @Param("flightClass") String flightClass,
                   @Param("username") String username);
}