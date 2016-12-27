package com.malinki.pz.dal.operations;

import com.malinki.pz.lib.UserDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.DatabaseUserOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class UserRegistration extends DatabaseUserOperation {

	private UserDTO user;
	private Logger logger = Logger.getLogger(UserRegistration.class);

	public UserRegistration(UserDTO user) {
		this.user = user;
	}

	@Override
	protected UserDTO mainAction() {
		boolean isUserPositivelyValidated = false;
		boolean hasErrorOccurred = false;

		try{
			isUserPositivelyValidated = isUserPositivelyValidated(user);
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccurred = true;
		}

		if(hasErrorOccurred)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
		else if(isUserPositivelyValidated) {
			try{
				mapper.registerUser(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getEmail());
				mapper.commit();
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_REGISTERED_SUCCESSFULLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_ALREADY_EXIST;

		return null;
	}

	private boolean isUserPositivelyValidated(UserDTO user) {
		boolean isLoginAlreadyUsed = getBoolean(mapper.isLoginAlreadyUsed(user.getUsername()));
		return !isLoginAlreadyUsed;
	}
}
