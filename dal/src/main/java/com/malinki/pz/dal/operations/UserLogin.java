package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.TicketMapper;
import com.malinki.pz.dal.UserMapper;
import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.UserDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class UserLogin extends DatabaseComplexResponseOperation {

	private UserDTO userForLoginValidation;
	private final Logger logger = Logger.getLogger(UserLogin.class);

	public UserLogin(UserDTO user) {
		super(UserMapper.class);
		this.userForLoginValidation = user;
	}

	@Override
	protected MalinkiComplexResponse mainAction() {
		boolean isUsernameAndPasswordCorrect = false;
		boolean hasErrorOccurred = false;

		try{
			isUsernameAndPasswordCorrect = getBoolean(((UserMapper)mapper).isUsernameAndPasswordCorrect(userForLoginValidation));
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccurred = true;
		}

		UserDTO user = null;

		if(hasErrorOccurred)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
		else if(isUsernameAndPasswordCorrect){
			try{
				user = ((UserMapper)mapper).getUserByUsername(userForLoginValidation.getUsername());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOGGED_IN_SUCCESSFULLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD;

		MalinkiComplexResponse userResponse = new MalinkiComplexResponse();
		userResponse.setDtoResult(user);

		return userResponse;
	}
}
