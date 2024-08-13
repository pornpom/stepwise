package com.stepwise.stepwise.service;

import com.stepwise.stepwise.entity.ConfUserEntity;
import com.stepwise.stepwise.repository.ConfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ConfUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(ConfUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ConfUserEntity user = userRepository.findByUserUsernameAndStatus(username , "A").orElseThrow(() -> new UsernameNotFoundException("User not found"));;

        return new org.springframework.security.core.userdetails.User(
                user.getUserUsername(),
                user.getUserPassword(),
                new ArrayList<>()); // Provide authorities if needed
    }
}