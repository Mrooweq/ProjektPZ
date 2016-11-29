package com.malinki.pz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.Application;
import com.malinki.pz.Mapper;


@RestController
@RequestMapping(value="/api")            
public class Controller {

	private Logger logger = Logger.getLogger(ClassName.class.getName());
	
	@RequestMapping(value = "/login")
	public void login(@RequestBody String requestBody, HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString("login");		
		String password = jsonObject.getString("password");	

		boolean isActionFinishedSuccesfully = registerUser(login, password);
		setResponse(response, isActionFinishedSuccesfully);
	}

	private boolean registerUser(String login, String password) {
		InputStream inputStream = null;
		boolean isActionFinishedSuccesfully = false;
		
		try {
			inputStream = Resources.getResourceAsStream(Application.CONFIG_FILE_NAME);
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		
		try {				
			Mapper mapper = session.getMapper(Mapper.class);			
			mapper.addUser(login, password);
			mapper.commit();
			
			isActionFinishedSuccesfully = true;
		} catch(Exception e){
			logger.log(Level.ERROR, e.toString());
		} finally {
			session.close();
			closeInputStream(inputStream);
		}
		
		return isActionFinishedSuccesfully;
	}



	private void setResponse(HttpServletResponse response, boolean isActionFinishedSuccesfully){
		OutputStreamWriter writer = null;
		
		try{
			if(isActionFinishedSuccesfully)
				response.setStatus(HttpServletResponse.SC_OK);
			else
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			writer = new OutputStreamWriter(response.getOutputStream());

			if(isActionFinishedSuccesfully)
				writer.write("Zarejestrowano uzytkownika");
			else
				writer.write("Nie udalo sie zarejestrowac");

			writer.flush();
		} catch(IOException e){
			logger.log(Level.ERROR, e.toString());
		}
		finally{
			try {
				writer.close();
			} catch (IOException e) {
				logger.log(Level.ERROR, e.toString());
			}
		}
	}

	private void closeInputStream(InputStream inputStream){
		try {
			inputStream.close();
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}
	}
}
