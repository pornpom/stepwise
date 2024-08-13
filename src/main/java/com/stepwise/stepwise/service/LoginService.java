package com.stepwise.stepwise.service;

import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.entity.ConfUserEntity;
import com.stepwise.stepwise.repository.ConfUserRepository;
import com.stepwise.stepwise.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public ConfUserDto login(ConfUserDto dto){
            try{

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
                String username = URLDecoder.decode(dto.getUserUsername(), StandardCharsets.UTF_8);
                String password = URLDecoder.decode(dto.getUserPassword(), StandardCharsets.UTF_8);

                ConfUserEntity  confUserEntity = confUserRepository.findFirstByUserUsernameAndStatus(username , "A");
                if(confUserEntity != null &&  bCryptPasswordEncoder.matches(password,confUserEntity.getUserPassword())) {
                    ConfUserDto result = new ConfUserDto();
                    result.setAccessToken(JwtUtil.generateToken(confUserEntity));
                    result.setRefreshToken(JwtUtil.generateRefreshToken(confUserEntity));
                    return result;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

    public ConfUserDto refreshTokens(String refreshToken) {
        if (JwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token is expired");
        }
        String username = JwtUtil.extractUsername(refreshToken);
        if(StringUtils.isNotEmpty(username)){
            ConfUserEntity  confUserEntity = confUserRepository.findFirstByUserUsernameAndStatus(username , "A");
            ConfUserDto result = new ConfUserDto();
            result.setAccessToken(JwtUtil.generateToken(confUserEntity));
            result.setRefreshToken(JwtUtil.generateRefreshToken(confUserEntity));
            return result;
        }
       return null;
    }
}
