package com.malinki.pz.bll.validation;


import com.malinki.pz.lib.entity.UserUVM;

public class SessionStorage {
    private UserUVM userUVM;
    private TokenContainer tokenContainer;

    public SessionStorage(UserUVM userUVM, TokenContainer tokenContainer){
        this.userUVM = userUVM;
        this.tokenContainer = tokenContainer;
    }

    public UserUVM getUser(){
        return userUVM;
    }

    public TokenContainer getTokenContainer(){
        return tokenContainer;
    }
}
