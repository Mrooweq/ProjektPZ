package com.malinki.pz.bll;

import com.malinki.pz.lib.UserUVM;

public class UserContext {

	private UserUVM currentUser;
	
	public void setCurrentUser(UserUVM currentUser){
		this.currentUser = currentUser;
	}
	
	public UserUVM getCurrentUser(){
		return currentUser;
	}
	
	public void clearCurrentUser(){
		currentUser = null;
	}
}
