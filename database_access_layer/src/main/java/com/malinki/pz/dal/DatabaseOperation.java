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

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.constants.Strings;

public abstract class DatabaseOperation {
	private Logger logger = Logger.getLogger(DatabaseOperation.class);
	protected Mapper mapper;
	protected HttpServletResponse response;
	protected DatabaseOperationResultEnum databaseOperationResultEnum;
	
	public DatabaseOperation(HttpServletResponse response){
		this.response = response;
	}

	public void performAction() {	
		InputStream inputStream = openInputStream();
		SqlSession session = establishSession(inputStream);
		mapper = session.getMapper(Mapper.class);

		try {				
			mainAction();
		} catch(Exception e){
			logger.log(Level.ERROR, e.toString());
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_ADD_ATTEMPT_FAILED_DUE_TO_ERROR;
		} finally {
			setResponse();
			session.close();
			closeInputStream(inputStream);
		}
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


	private void setResponse() {
		OutputStreamWriter writer = null;

		try{
			writer = new OutputStreamWriter(response.getOutputStream());

			if(databaseOperationResultEnum == DatabaseOperationResultEnum.USER_ADDED_PROPERLY) {
				logger.log(Level.INFO, String.format(Strings.USER_ADDED_PROPERLY_INFO, mapper.getLastAddedUser().getLogin()));
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else if(databaseOperationResultEnum == DatabaseOperationResultEnum.USER_ALREADY_EXIST) {
				logger.log(Level.INFO, String.format(Strings.USER_ALREADY_EXISTS, mapper.getLastAddedUser().getLogin()));
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
			else if(databaseOperationResultEnum == DatabaseOperationResultEnum.USER_ADD_ATTEMPT_FAILED_DUE_TO_ERROR) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			writer.write(databaseOperationResultEnum.getName());
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

	abstract protected void mainAction();
}
