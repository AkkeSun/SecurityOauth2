package com.example.securityoauth2.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class OwnerService implements UserDetailsService {


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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("USER IS NOT EXISTS"));
        return owner;
    }
}


