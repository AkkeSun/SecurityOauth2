package com.example.securityoauth2.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Oauth_TokenRepository extends JpaRepository<Oauth_Token, String>{
    void deleteByUsername(String username);
    Oauth_Token findByUsername(String username);
}
