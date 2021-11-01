package com.example.securityoauth2.client;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of="id")
@Table(name = "oauth_client_details")
public class Client {

    @Id
    private String client_id;
    private String client_secret;
    private String scope = "read,write";
    private String authorized_grant_types = "authorization_code,refresh_token";
    private String web_server_redirect_uri = "http://localhost:8082/oauth2/callback";
    private String authorities = "ROLE_USER";
    private Integer access_token_validity = 30000;
    private Integer refresh_token_validity = 50000;
    private String resource_ids = null;
    private String additional_information = null;
    private String autoapprove = null;
}
