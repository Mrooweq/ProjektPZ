package com.malinki.pz.bll;

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
