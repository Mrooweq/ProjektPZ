package com.malinki.pz.bll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.malinki.pz.bll.constants.Strings;
import com.malinki.pz.dal.UserRepository;

@SpringBootApplication
public class Application {


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		new ClassPathXmlApplicationContext(Strings.SPRING_CONFIG_FILE_NAME);
	}
}
