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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.Application;
import com.malinki.pz.Mapper;


@WebServlet
@RestController
@RequestMapping(value="/api")            
public class Controller {

	@RequestMapping			//@RequestMapping("/animals")
	public List<User> getLol(HttpServletRequest request, HttpServletResponse response) {					
		String login = request.getParameter("login");
		String password = request.getParameter("password");


		////////
		
		System.out.println("ZZZZZZZZZZZZZZZZZZZZZ");
		
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
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("Zarejestrowano uzytkownika");
				response.setStatus(HttpServletResponse.SC_OK);
				writer.flush();
				writer.close();
			} catch (Exception e){}
			
		} catch(Exception e){
			try {
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("Nie udalo sie zarejestrowac");
				response.setStatus(HttpServletResponse.SC_OK);
				writer.flush();
				writer.close();
			} catch (Exception e2){}
		} finally {
			session.close();
		}


		//////


		ArrayList<User> users = new ArrayList<>();
		users.add(user);

		return users;
	}


	private void doGet(HttpServletRequest request){
		System.out.println("lolXXXXXXXX: " + request.getParameter("param"));
	}

	private HashMap doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			writer.write("Mam 5 gram");
			writer.flush();
			writer.close();
		} catch (Exception e){}

		HashMap <String, String> map = new HashMap <String, String>();
		map.put("login", request.getParameter("login"));
		map.put("password", request.getParameter("password"));

		return map;
	}
}
