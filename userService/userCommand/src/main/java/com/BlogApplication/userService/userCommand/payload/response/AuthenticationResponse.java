package com.BlogApplication.userService.userCommand.payload.response;

public class AuthenticationResponse {
    private String jwt;

    public AuthenticationResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
