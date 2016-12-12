package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.Temp;

@Service
public class UserRepository implements IUserRepository {
		
	@Autowired
	public Temp temp;
		
	@Override
	public void registerUser(HttpServletResponse response, String login, String password) {
		temp.registerUser(response, login, password);
	}
}
