package com.stepwise.stepwise.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@Slf4j
@Component
public class OauthUser {

  public String oauthUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      String principal = (String) authentication.getPrincipal();
      if(StringUtils.isNotEmpty(principal)){
        return principal;
      }
    }
    return "";
  }

  public String getToken() {
    return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader("Authorization");
  }

  public static int oauthUserId() {
    Map<String, Object> additionalInfo = getAdditionalInfo();
    if (additionalInfo != null) {
      int userId = (int) additionalInfo.get("userId");
      return userId;
    }
    return 0;
  }

  private static Map<String, Object> getAdditionalInfo() {
    if (RequestContextHolder.getRequestAttributes() != null){
      String header = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
      if(header != null && header.split("\\.").length == 3){
        String string = new String(Base64.getDecoder().decode(header.split("\\.")[1]));
        try {
          Map<String,Object> result = new ObjectMapper().readValue(string, HashMap.class);
          return result;
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      }
    }
    return null;
  }

}
