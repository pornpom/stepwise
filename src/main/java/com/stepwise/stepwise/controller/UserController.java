package com.stepwise.stepwise.controller;


import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.service.UserService;
import com.stepwise.stepwise.utils.HelperUtils;
import com.stepwise.stepwise.utils.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/save-user")
    public ResponseEntity<ResponseModel> saveUser(@RequestBody ConfUserDto dto) {
        try {
            ConfUserDto result =  userService.saveUser(dto);
            if(result != null){
                return HelperUtils.responseSuccess(result);
            }else{
                return HelperUtils.responseError(HttpStatus.BAD_REQUEST, "BAD REQUEST", "CAN NOT SAVE USER");
            }

        } catch ( Exception e) {
            return HelperUtils.responseError(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage());
        }
    }

}
