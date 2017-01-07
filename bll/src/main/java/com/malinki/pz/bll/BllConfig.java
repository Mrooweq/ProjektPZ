package com.malinki.pz.bll;

import com.malinki.pz.dal.FlightRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malinki.pz.dal.UserRepository;

@Configuration
public class BllConfig {
       
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public AirportRepository airportRepository() {
        return new AirportRepository();
    }

    @Bean
    public FlightRepository flightRepository() {
        return new FlightRepository();
    }
}