package com.malinki.pz.dal.operations;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.DatabaseOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.domain.UserDTO;

public class UserRegistration extends DatabaseOperation{

	private UserDTO user;
	private Logger logger = Logger.getLogger(UserRegistration.class);

	public UserRegistration(HttpServletResponse response, UserDTO user) {
		super(response);
		this.user = user;
	}

	@Override
	protected void mainAction() {
		boolean isUserPositivelyValidated = false;
		boolean hasErrorOccured = false;

		try{
			isUserPositivelyValidated = isUserPositivelyValidated(user);
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccured = true;
		}

		if(isUserPositivelyValidated && !hasErrorOccured) {
			try{
				mapper.registerUser(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getEmail());
				mapper.commit();	
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_REGISTERED_PROPERLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else if(!isUserPositivelyValidated && !hasErrorOccured)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_ALREADY_EXIST;
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
	}

	private boolean isUserPositivelyValidated(UserDTO user) {
		boolean isLoginAlreadyUsed = getBoolean(mapper.isLoginAlreadyUsed(user.getUsername()));
		return !isLoginAlreadyUsed;
	}
}
