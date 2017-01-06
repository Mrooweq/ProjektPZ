package com.malinki.pz.dal;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.UserResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public abstract class DatabaseUserOperation extends DatabaseOperation{
	private Logger logger = Logger.getLogger(DatabaseUserOperation.class);
	protected UserMapper userMapper;
	protected DatabaseOperationResultEnum databaseOperationResultEnum;

	public UserResponse performAction() {
		InputStream inputStream = openInputStream();
		SqlSession session = establishSession(inputStream);
		userMapper = session.getMapper(UserMapper.class);

		UserResponse userResponse;
		userResponse = mainAction();
		userResponse.setResult(getResultCode());

		session.close();
		closeInputStream(inputStream);

		return userResponse;
	}

	private int getResultCode() {
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

	abstract protected UserResponse mainAction();
}
