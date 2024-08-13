package com.stepwise.stepwise.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfUserDto   {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userUsername;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userFname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userLname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String identificationNo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassword;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;


}