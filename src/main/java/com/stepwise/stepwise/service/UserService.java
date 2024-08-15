package com.stepwise.stepwise.service;

import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.entity.ConfUserEntity;
import com.stepwise.stepwise.repository.ConfUserRepository;
import com.stepwise.stepwise.repository.JdbcTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {
    private final JdbcTemplateRepository jdbcTemplateRepository;
    private final ModelMapper modelMapper;
    private final ConfUserRepository confUserRepository;

    public UserService(ConfUserRepository confUserRepository, ModelMapper modelMapper, JdbcTemplateRepository jdbcTemplateRepository) {
        this.confUserRepository = confUserRepository;
        this.modelMapper = modelMapper;

        this.jdbcTemplateRepository = jdbcTemplateRepository;
    }

    public ConfUserDto saveUser(ConfUserDto dto){

        if(StringUtils.isNotEmpty(dto.getUserUsername()) && StringUtils.isNotEmpty(dto.getUserPassword())){
            if(dto.getUserId() == null){
                // check username is already
                ConfUserEntity confUserEntityCheck = confUserRepository.findFirstByUserUsernameAndStatus(dto.getUserUsername() , "A");
                if(confUserEntityCheck != null){
                    return null;
                }
            }
            ConfUserEntity confUserEntity = modelMapper.map(dto ,ConfUserEntity.class );
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
            confUserEntity.setUserPassword(encoder.encode(dto.getUserPassword()));
            confUserRepository.save(confUserEntity);
            return dto;
        }

        return null;
    }

    public Page<ConfUserDto> getListUser( ConfUserDto dto){
        return jdbcTemplateRepository.getListUser(dto);
    }


}
