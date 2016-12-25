package com.malinki.pz.dal.operations;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.DatabaseOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.domain.UserDTO;

public class UserLogin extends DatabaseOperation{

	private UserDTO userForLoginValidation;
	private Logger logger = Logger.getLogger(UserLogin.class);

	public UserLogin(UserDTO user) {
		this.userForLoginValidation = user;
	}

	@Override
	protected boolean mainAction() {
		boolean isUsernameAndPasswordCorrect = false;
		boolean hasErrorOccured = false;
		
		try{
			isUsernameAndPasswordCorrect = getBoolean(
					mapper.isUsernameAndPasswordCorrect(userForLoginValidation.getUsername(),
					userForLoginValidation.getPassword()));
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccured = true;
		}
		
		if(isUsernameAndPasswordCorrect && !hasErrorOccured){
			UserDTO user = null;
			
			try{
				user = mapper.getUserByUsername(userForLoginValidation.getUsername());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOGGED_IN_SUCCESSFULLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else if(!isUsernameAndPasswordCorrect && !hasErrorOccured)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD;
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
		
		return databaseOperationResultEnum == DatabaseOperationResultEnum.USER_LOGGED_IN_SUCCESSFULLY;
	}
}
