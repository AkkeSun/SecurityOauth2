package com.example.securityoauth2.token;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder @Getter @Setter @ToString
@EqualsAndHashCode(of="username")
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Oauth_Token {
    @Id
    private String username;
    private String token;
    private String refresh_token;
}