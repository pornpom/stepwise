package com.stepwise.stepwise.controller;

import com.stepwise.stepwise.dto.TblMdSiteDto;
import com.stepwise.stepwise.service.SiteService;
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
@RequestMapping("/api/site")
public class SiteController {
    private SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping(value = "/save-site")
    public ResponseEntity<ResponseModel> saveSite(@RequestBody TblMdSiteDto dto) {
        try {
            TblMdSiteDto result =  siteService.saveSite(dto);
            if(result != null){
                return HelperUtils.responseSuccess(result);
            }else{
                return HelperUtils.responseError(HttpStatus.BAD_REQUEST, "BAD REQUEST", "CAN NOT SAVE SITE");
            }

        } catch ( Exception e) {
            return HelperUtils.responseError(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage());
        }
    }
}
