package com.malinki.pz.lib;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lenovo on 2016-12-25.
 */
public class UserResponse {
    private UserDTO user;
    private int result;

    public UserResponse() {
        this.result = HttpServletResponse.SC_BAD_REQUEST;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
