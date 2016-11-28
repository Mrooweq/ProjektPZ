package com.malinki.pz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.malinki.pz.controller.Animal;

@SpringBootApplication
public class Application {

	private static final String CONFIG_FILE_NAME = "mybatis-config.xml";
	private static final String DZIALA = "DZIAŁA";

	private static String URL = "http://localhost:8080/api/animals";

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

			sendPost();


		} finally {
			session.close();
		}

	}

	private static void sendPost(){
		try{

			URL url = new URL(URL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			writer.write("value=1&anotherValue=10");
			writer.close();

			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println("xD: " + line);
			}

			reader.close();

		} catch(Exception e){}
	}

	private void sendGet(){
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
	}
}
