package com.malinki.pz.bll;

import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.UserUVM;

public interface IUserOperations {
	public int registerUser(UserUVM user);
	public MalinkiComplexResponse loginUser(UserUVM user);
	public void logoutUser(UserUVM user);
}
