package com.malinki.pz.bll;

import com.malinki.pz.dal.FlightRepository;
import com.malinki.pz.dal.TicketRepository;
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
    public FlightRepository flightRepository() {
        return new FlightRepository();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepository();
    }

    @Bean
    public EmailAndPdfService emailAndPdfService() {
        return new EmailAndPdfService();
    }

    @Bean
    public SendPDFByEmail sendPDFByEmail() {
        return new SendPDFByEmail();
    }
}