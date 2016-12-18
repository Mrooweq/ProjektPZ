package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.UserDTO;

public interface IUserRepository {
	public void registerUser(HttpServletResponse response, UserUVM user);
}
