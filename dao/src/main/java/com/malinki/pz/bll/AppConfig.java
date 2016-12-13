package com.malinki.pz.bll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malinki.pz.dal.Temp;
import com.malinki.pz.dao.UserRepository;

@Configuration
public class AppConfig {
	
    @Bean
    public Temp temp() {
        return new Temp();
    }
    
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}