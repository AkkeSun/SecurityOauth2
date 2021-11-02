package com.example.securityoauth2.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class OwnerService  {


    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public Owner saveOwner(Owner owner){
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepository.save(owner);
    }

    @Transactional
    public Optional<Owner> findByUsername(String name){
        return ownerRepository.findByUsername(name);
    }
}


