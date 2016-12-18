package com.malinki.pz.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malinki.pz.bll.UserOperations;
import com.malinki.pz.dal.UserRepository;

@Configuration
public class AppConfig {
    
    @Bean
    public UserOperations userOperations() {
        return new UserOperations();
    }
    
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}