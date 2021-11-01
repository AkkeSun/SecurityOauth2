package com.example.securityoauth2.client;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDto {
    private String client_id;
    private String client_secret;
}
