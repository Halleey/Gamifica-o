package com.presente.confeitaria.jwts;

public class JwtToken {

    private String Token;


    public JwtToken(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
