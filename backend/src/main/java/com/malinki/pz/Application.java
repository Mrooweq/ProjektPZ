package com.malinki.pz;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.malinki.pz.controller.Animal;

@SpringBootApplication
public class Application {
	
	private static final String CONFIG_FILE_NAME = "mybatis-config.xml";
	private static final String DZIALA = "DZIAŁA";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println(DZIALA);

		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(CONFIG_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			Mapper mapper = session.getMapper(Mapper.class);
			Animal animal = mapper.getAnimal(2);
			System.out.println("lol: " + animal.getId());
			System.out.println("lol: " + animal.getName());
			System.out.println("lol: " + animal.getAge());
			
			////
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", "3");
			params.put("name", "Wydra");
			params.put("age", 5);
						
			mapper.addAnimal(params);
			mapper.commit();

		} finally {
			session.close();
		}

	}
}
