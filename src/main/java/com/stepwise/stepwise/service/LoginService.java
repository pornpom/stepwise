package com.stepwise.stepwise.service;

import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.entity.ConfUserEntity;
import com.stepwise.stepwise.repository.ConfUserRepository;
import com.stepwise.stepwise.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class LoginService {
    private final UserDetailsService userDetailsService;


    private final ConfUserRepository confUserRepository;

    public LoginService(ConfUserRepository confUserRepository, UserDetailsService userDetailsService) {
        this.confUserRepository = confUserRepository;

        this.userDetailsService = userDetailsService;

    }

    public String login(ConfUserDto dto){
            try{

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
                String username = URLDecoder.decode(dto.getUserUsername(), StandardCharsets.UTF_8);
                String password = URLDecoder.decode(dto.getUserPassword(), StandardCharsets.UTF_8);

                ConfUserEntity  confUserEntity = confUserRepository.findFirstByUserUsernameAndStatus(username , "A");
                if(confUserEntity != null &&  bCryptPasswordEncoder.matches(password,confUserEntity.getUserPassword())) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    return   JwtUtil.generateToken(confUserEntity);

                }



            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
}
