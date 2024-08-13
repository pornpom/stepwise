package com.stepwise.stepwise.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private Object result;
    private String statusDescription;
    private String status;
    private int statusCode;

}
