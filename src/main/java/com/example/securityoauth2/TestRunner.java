package com.example.securityoauth2;

import com.example.securityoauth2.client.Client;
import com.example.securityoauth2.client.ClientDto;
import com.example.securityoauth2.client.ClientService;
import com.example.securityoauth2.owner.Owner;
import com.example.securityoauth2.owner.OwnerService;
import com.example.securityoauth2.owner.Owner_Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    ClientService clientService;

    @Autowired
    OwnerService ownerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        ClientDto dto = ClientDto.builder()
                .client_id("lowlow")
                .client_secret("admin")
                .build();
        clientService.createClient(dto);


        Owner saverOwner = Owner.builder()
                .id(1L)
                .username("admin@naver.com")
                .password("admin")
                .roles(Set.of(Owner_Roles.ROLE_ADMIN))
                .build();
        ownerService.saveOwner(saverOwner);

    }
}
