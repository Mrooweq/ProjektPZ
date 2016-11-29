package com.malinki.pz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.controller.Controller;

public class UserRepository {
	private Logger logger = Logger.getLogger(Controller.class);
	
	public void registerUser(HttpServletResponse response, String login, String password) {
		InputStream inputStream = openInputStream();
		SqlSession session = establishSession(inputStream);
		boolean isActionFinishedSuccesfully = false;
		
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
		
		setResponse(response, isActionFinishedSuccesfully);
	}
	
	private SqlSession establishSession(InputStream inputStream){
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		return sqlSessionFactory.openSession();
	}

	private InputStream openInputStream(){
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(Application.MYBATIS_CONFIG_FILE_NAME);
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}
		
		return inputStream;
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
