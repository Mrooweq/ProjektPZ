package com.malinki.pz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.Application;
import com.malinki.pz.Mapper;


@RestController
@RequestMapping(value="/api")            
public class Controller {

	@RequestMapping(value = "/login")
	public void login(@RequestBody String request) {		
		JSONObject jsonObject = new JSONObject(request);
        String login = jsonObject.getString("login");		
        String password = jsonObject.getString("password");	
        
		System.out.println("lol1: " + login);
		System.out.println("lol2: " + password);
	}
	
	
	
	@RequestMapping			
	public List<User> getLol(HttpServletRequest request, HttpServletResponse response) {					
		String login = request.getParameter("login");
		String password = request.getParameter("password");

			
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(Application.CONFIG_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();

		User user = null;
		
		try {			
			Mapper mapper = session.getMapper(Mapper.class);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", 1);
			params.put("login", login);
			params.put("password", password);

			mapper.deleteAllUsers(); ////////////
			mapper.addUser(params);
			mapper.commit();
			
			user = mapper.getUser(1);
			
			try {
				response.setStatus(HttpServletResponse.SC_OK);
				
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("Zarejestrowano uzytkownika");
				writer.flush();
				writer.close();
			} catch (Exception e){}
			
		} catch(Exception e){
			try {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("Nie udalo sie zarejestrowac");
				writer.flush();
				writer.close();
			} catch (Exception e2){}
		} finally {
			session.close();
		}


		ArrayList<User> users = new ArrayList<>();
		users.add(user);

		return users;
	}
}
