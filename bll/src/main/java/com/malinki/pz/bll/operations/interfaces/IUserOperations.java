package com.malinki.pz.bll.operations.interfaces;

import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.UserUVM;

public interface IUserOperations {
	public int registerUser(UserUVM user);
	public MalinkiComplexResponse loginUser(UserUVM user);
	public void logoutUser(UserUVM user);
	public boolean validateUserByToken(String username, String token);
}
