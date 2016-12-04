package com.malinki.pz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import constants.Strings;


@SpringBootApplication
public class Application {


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		new ClassPathXmlApplicationContext(Strings.SPRING_CONFIG_FILE_NAME);
	}
}
