package com.example.securityoauth2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientRunner implements ApplicationRunner {

    @Autowired
    ClientService clientService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*
        ClientDto dto = ClientDto.builder()
                .client_id("sun")
                .client_secret("pass")
                .build();
        clientService.createClient(dto);
         */
    }
}
