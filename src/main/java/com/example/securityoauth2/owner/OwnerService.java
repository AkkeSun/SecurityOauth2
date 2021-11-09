package com.example.securityoauth2.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OwnerService implements UserDetailsService {


    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("USER IS NOT EXISTS"));
        return owner;
    }


    @Transactional
    public Owner saveOwner(Owner owner){
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepository.save(owner);
    }

    @Transactional
    public Optional<Owner> findByUsername(String name){
        return ownerRepository.findByUsername(name);
    }

    @Transactional
    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }
}


