package com.malinki.pz.lib;

import javax.servlet.http.HttpServletResponse;


public class UserResponse {
    private UserDTO userDTO;
    private UserUVM userUVM;
    private int result;

    public UserResponse() {
        this.result = HttpServletResponse.SC_BAD_REQUEST;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO user) {
        this.userDTO = user;
    }

    public UserUVM getUserUVM() {
        return userUVM;
    }

    public void setUserUVM(UserUVM user) {
        this.userUVM = user;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
