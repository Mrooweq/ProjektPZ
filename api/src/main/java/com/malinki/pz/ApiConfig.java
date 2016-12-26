package com.malinki.pz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.malinki.pz.bll.BllConfig;
import com.malinki.pz.bll.SessionTable;
import com.malinki.pz.bll.UserOperations;

@Configuration
@Import(BllConfig.class)
public class ApiConfig {
    
    @Bean
    public UserOperations userOperations() {
        return new UserOperations();
    }
    
    @Bean
    public SessionTable userContext() {
        return new SessionTable();
    }
}