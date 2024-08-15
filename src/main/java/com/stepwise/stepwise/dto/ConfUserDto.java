package com.stepwise.stepwise.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConfUserDto   {
    @JsonIgnore
    private int page;
    @JsonIgnore
    private Integer perPage;
    @JsonIgnore
    private Sort.Direction direction;
    @JsonIgnore
    private String sort;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String allSearch;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer annualLeave;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer personalLeave;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer medicalLeave;





}