package com.malinki.pz;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.malinki.pz.controller.Animal;

@SpringBootApplication
public class Application {

	private static final String CONFIG_FILE_NAME = "mybatis-config.xml";
	private static final String DZIALA = "DZIAŁA";

	private static String URL = "http://localhost:8080/api/animals";
	
	final static Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println(DZIALA);

		logger.error("LOLLLLLL");
		
		
		
		
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

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", 1);
			params.put("name", "Wydra");
			params.put("age", 5);

			mapper.deleteAllAnimals(); ////////////
			mapper.addAnimal(params);
			mapper.commit();

			Animal animal = mapper.getAnimal(1);
			System.out.println("lol: " + animal.getId());
			System.out.println("lol: " + animal.getName());
			System.out.println("lol: " + animal.getAge());


			///////////

			URIBuilder b = null;
			try {
				b = new URIBuilder(URL);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			b.addParameter("param", "xD");


			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(b.toString());
			try {
				client.execute(request);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	

		} finally {
			session.close();
		}

	}
}
