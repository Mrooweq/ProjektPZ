package com.malinki.pz.dal;

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

import com.malinki.pz.dal.constants.Strings;

public abstract class DatabaseOperation {
	private Logger logger = Logger.getLogger(DatabaseOperation.class);

	public void performAction(HttpServletResponse response) {	
		InputStream inputStream = openInputStream();
		SqlSession session = establishSession(inputStream);
		boolean isActionFinishedSuccesfully = false;

		Mapper mapper = session.getMapper(Mapper.class);	

		try {				
			mainAction(mapper);
			logger.log(Level.INFO, String.format(Strings.USER_ADDED_PROPERLY_INFO, mapper.getLastUser().getLogin()));

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
			inputStream = Resources.getResourceAsStream(Strings.MYBATIS_CONFIG_FILE_NAME);
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}

		return inputStream;
	}


	private void setResponse(HttpServletResponse response, boolean isActionFinishedSuccesfully){
		OutputStreamWriter writer = null;

		try{
			writer = new OutputStreamWriter(response.getOutputStream());

			if(isActionFinishedSuccesfully){
				response.setStatus(HttpServletResponse.SC_OK);
				writer.write(Strings.USER_ADDED_PROPERLY);
			}
			else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				writer.write(Strings.USER__NOT_ADDED_PROPERLY);
			}

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

	abstract protected void mainAction(Mapper mapper);
}
