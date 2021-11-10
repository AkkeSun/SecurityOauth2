package com.example.securityoauth2.token;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oauth_TokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}