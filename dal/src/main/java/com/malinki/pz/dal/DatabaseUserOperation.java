package com.malinki.pz.dal;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.constants.Strings;

public abstract class DatabaseUserOperation {
	private Logger logger = Logger.getLogger(DatabaseUserOperation.class);
	protected UserMapper userMapper;
	protected DatabaseOperationResultEnum databaseOperationResultEnum;

	public UserResponse performAction() {
		UserResponse userResponse = new UserResponse();
		InputStream inputStream = openInputStream();
		SqlSession session = establishSession(inputStream);
		userMapper = session.getMapper(UserMapper.class);

		try {
			UserDTO user = mainAction();
			int result = setResponse();

			userResponse.setUserDTO(user);
			userResponse.setResult(result);
		} finally {
			session.close();
			closeInputStream(inputStream);
		}

		return userResponse;
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

	private int setResponse() {
		int result = 0;

		logger.log(Level.INFO, databaseOperationResultEnum.getName());

		switch (databaseOperationResultEnum){
			case USER_LOGGED_IN_SUCCESSFULLY:
				result = HttpServletResponse.SC_OK;
				break;
			case USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR:
				result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				break;
			case USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD:
				result = HttpServletResponse.SC_FORBIDDEN;
				break;

			case USER_REGISTERED_SUCCESSFULLY:
				result = HttpServletResponse.SC_OK;
				break;
			case USER_REGISTER_ATTEMPT_FAILED_DUE_TO_ERROR:
				result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				break;
			case USERNAME_ALREADY_USED:
				result = HttpServletResponse.SC_CONFLICT;
				break;
			case EMAIL_ALREADY_USED:
				result = HttpServletResponse.SC_NOT_ACCEPTABLE;
				break;
			default:
				break;
		}

		return result;
	}

	private void closeInputStream(InputStream inputStream){
		try {
			inputStream.close();
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}
	}

	protected boolean getBoolean(int dual){
		if(dual == 1)
			return true;
		else
			return false;
	}

	abstract protected UserDTO mainAction();
}
