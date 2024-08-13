package com.stepwise.stepwise.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfUserDto   {

    private Integer userId;
    private String userUsername;
    private String userFname;
    private String userLname;
    private String status;
    private Integer createBy;
    private String email;
    private String identificationNo;
    private String userPassword;
    private String userType;


}