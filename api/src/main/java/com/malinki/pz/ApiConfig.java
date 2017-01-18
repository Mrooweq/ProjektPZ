package com.malinki.pz;

import com.malinki.pz.bll.*;
import com.malinki.pz.bll.services.FlightService;
import com.malinki.pz.bll.services.TicketService;
import com.malinki.pz.bll.services.UserService;
import com.malinki.pz.bll.validation.SessionTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BllConfig.class)
public class ApiConfig {

    @Bean
    public SessionTable userContext() {
        return new SessionTable();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public FlightService flightService() {
        return new FlightService();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }
}