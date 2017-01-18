package com.malinki.pz.bll.validation;


import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenContainer {
    private String token;
    private final int BITS_NUMBER = 130;
    private final int TOKEN_SIZE = 32;
    private SecureRandom secureRandom;

    public TokenContainer(){
        secureRandom = new SecureRandom();
        generateNewToken();
    }

    public void generateNewToken() {
        token = new BigInteger(BITS_NUMBER, secureRandom).toString(TOKEN_SIZE);
    }

    public String getToken(){
        return token;
    }
}
