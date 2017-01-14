package com.malinki.pz.bll;

import com.malinki.pz.lib.UserUVM;

import java.util.HashMap;

public class SessionTable {

	private HashMap<String, SessionStorage> sessionTable;

	public SessionTable(){
		sessionTable = new HashMap<>();
	}

	public void addUser(UserUVM user){
		sessionTable.put(user.getUsername(), new SessionStorage(user, new TokenContainer()));
	}

	public SessionStorage getUserSession(UserUVM user){
		return sessionTable.get(user.getUsername());
	}

	public void deleteUserSession(UserUVM user){
		sessionTable.remove(user.getUsername());
	}

	public boolean validateUserByToken(String username, String token){
		SessionStorage sessionStorage = sessionTable.get(username);

		if(sessionStorage != null)
			return sessionStorage.getTokenContainer().getToken().equals(token);
		else
			return false;
	}
}
