package com.malinki.pz.bll;

public class UserUVM {
	private String login;
	private String password;
	
	public UserUVM(){}

	public UserUVM(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
