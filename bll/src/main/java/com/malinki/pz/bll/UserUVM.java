package com.malinki.pz.bll;

public class UserUVM {
	private String login;
	private String password;
	
	public UserUVM(){}

    private UserUVM(UserUVMBuilder builder) {
        this.login = builder.login;
        this.password = builder.password;
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
	
	
	public static class UserUVMBuilder {
		private String login;
		private String password;

	    public UserUVMBuilder login(String login) {
	        this.login = login;
	        return this;
	    }

	    public UserUVMBuilder password(String password) {
	        this.password = password;
	        return this;
	    }

	    public UserUVM build() {
	        return new UserUVM(this);
	    }
	}
}
