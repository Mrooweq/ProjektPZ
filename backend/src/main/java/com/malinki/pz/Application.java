package com.malinki.pz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final String CONFIG_FILE_NAME = "mybatis-config.xml";;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
