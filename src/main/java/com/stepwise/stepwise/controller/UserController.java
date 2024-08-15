package com.stepwise.stepwise.controller;


import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.service.UserService;
import com.stepwise.stepwise.utils.HelperUtils;
import com.stepwise.stepwise.utils.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/list-user")
    public ResponseEntity<ResponseModel> getDecisionDetail(
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", value = "perPage") Integer perPage,
            @RequestParam(required = false, defaultValue = "createDt", value = "sort") String sort,
            @RequestParam(name = "allSearch", required = false) String allSearch

    ) {
        try {
            ConfUserDto dto = ConfUserDto.builder().page(page).perPage(perPage).sort(sort).allSearch(allSearch).build();

            Page<ConfUserDto> result = userService.getListUser(dto);
            return HelperUtils.responseSuccess(result);
        } catch ( Exception e) {
            return HelperUtils.responseError(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage());
        }
    }

}
