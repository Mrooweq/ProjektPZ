package com.malinki.pz.bll;

import com.malinki.pz.dal.repositories.FlightRepository;
import com.malinki.pz.dal.repositories.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malinki.pz.dal.repositories.UserRepository;

@Configuration
public class BllConfig {
       
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public FlightRepository flightRepository() {
        return new FlightRepository();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepository();
    }
}