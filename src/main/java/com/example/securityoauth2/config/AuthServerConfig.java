package com.example.securityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @Override
    // 클라이언트에 대한 정보를 DB를 통해 관리 
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        /*
        clients.inMemory()
                        .withClient("admin")
                        .secret(passwordEncoder.encode("admin"))
                        .redirectUris("http://localhost:8082/oauth2/callback")
                        .authorizedGrantTypes("authorization_code")
                        .scopes("read", "write")
                        .accessTokenValiditySeconds(30000);

        */
       clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }


    @Override
    // 토큰에 대한 정보를 DB를 통해 관리
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new JdbcTokenStore(dataSource));
    }
}