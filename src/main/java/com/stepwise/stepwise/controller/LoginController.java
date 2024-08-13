package com.stepwise.stepwise.controller;

import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.service.LoginService;
import com.stepwise.stepwise.utils.HelperUtils;
import com.stepwise.stepwise.utils.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseModel> login(@RequestBody ConfUserDto dto) {
        try {
            String result =  loginService.login(dto);
            if(StringUtils.isNotEmpty(result)){
                return HelperUtils.responseSuccess(result);
            }else{
                return HelperUtils.responseError(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "CAN NOT LOGIN");
            }

        } catch ( Exception e) {
            return HelperUtils.responseError(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage());
        }
    }
}
