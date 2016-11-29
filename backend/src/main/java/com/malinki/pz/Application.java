package com.malinki.pz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {
	
	public static final String MYBATIS_CONFIG_FILE_NAME = "mybatis-config.xml";
	public static final String SPRING_CONFIG_FILE_NAME = "configuration.xml";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE_NAME);
	}
}
