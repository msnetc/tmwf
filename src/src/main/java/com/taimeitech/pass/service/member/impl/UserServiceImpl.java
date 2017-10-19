package com.taimeitech.pass.service.member.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taimeitech.framework.common.TaimeiRestTemplate;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.common.constant.ServiceUrl;
import com.taimeitech.pass.entity.ActionBaseResult;
import com.taimeitech.pass.entity.member.UserRoles;
import com.taimeitech.pass.service.member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import   com.taimeitech.pass.entity.member.UserRolesResult;

import java.util.Arrays;

@Component
public class UserServiceImpl implements UserService {
    @Value("${microService.baseUrl}")
    private String baseUrl;
    @Autowired
    private TaimeiRestTemplate restTemplate;

    @Override
    public UserRolesResult GetUsers(String talentId, String softWareId, String roleId) {
        String reqUrl = baseUrl + "/auth/userroles" + "?tenantId=" + talentId + "&softId=" + softWareId+ "&roleId=" + roleId;
        UserRolesResult result = restTemplate.getForObject(reqUrl, UserRolesResult.class);
        return result;
    }
}
