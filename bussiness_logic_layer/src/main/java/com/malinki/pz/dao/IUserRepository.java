package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.User;

public interface IUserRepository {
	public void registerUser(HttpServletResponse response, User user);
}
