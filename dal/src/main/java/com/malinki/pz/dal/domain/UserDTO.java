package com.malinki.pz.dal.domain;

public class UserDTO {

	private String login;
	private String password;
	
	public UserDTO(){}

    private UserDTO(UserDTOBuilder builder) {
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
	
	
	public static class UserDTOBuilder {
		private String login;
		private String password;

	    public UserDTOBuilder login(String login) {
	        this.login = login;
	        return this;
	    }

	    public UserDTOBuilder password(String password) {
	        this.password = password;
	        return this;
	    }

	    public UserDTO build() {
	        return new UserDTO(this);
	    }
	}
}
