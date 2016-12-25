package com.malinki.pz.bll;

import com.malinki.pz.lib.UserUVM;

public interface IUserRepository {
	public int registerUser(UserUVM user);
	public int loginUser(UserUVM user);
}
