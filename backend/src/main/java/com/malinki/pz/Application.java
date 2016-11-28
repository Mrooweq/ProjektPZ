package com.malinki.pz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

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

import com.malinki.pz.controller.User;

@SpringBootApplication
public class Application {

	public static final String CONFIG_FILE_NAME = "mybatis-config.xml";
	
	private static final String LOGIN_PATTERN = "login=%s&password=%s";
	private static String URL = "http://localhost:8080/api";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		sendPost();
	}

	private static void sendPost(){
		try{

			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			String login = "login";
			String password = "password";
			
			String loginParameters = String.format(LOGIN_PATTERN, login, password);

			writer.write(loginParameters);
			writer.close();

			if(conn.getResponseCode() == 200)
			{
				String line;
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					System.out.println("xD: " + line);
				}

				reader.close();	
			}

		} catch(Exception e){}
	}



	private static void sendGet(){
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
