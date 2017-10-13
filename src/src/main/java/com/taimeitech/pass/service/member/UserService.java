package com.taimeitech.pass.service.member;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taimeitech.framework.common.TaimeiRestTemplate;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.common.constant.ServiceUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Service
public class UserService {
    @Value("${microService.baseUrl}")
    private String baseUrl;
    @Autowired
    private TaimeiRestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getRandomFail")
    public ActionResult GetUsers(String talentId, String softWareId, String roleId) {

         MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("TM-Header-TenantId", talentId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);

        String reqUrl = baseUrl + "auth/userroles" + "?tenantId=" + talentId + "&softId=" + softWareId+ "&roleId=" + roleId;

        //Get方法1
//        ResponseEntity<T> restTemplate.exchange(reqUrl, HttpMethod.GET, httpEntity, ActionResult.class).getBody();
        //Get方法2
        //return restTemplate.getForObject(reqUrl,ResultJson.class);
        //Post方法
        // return restTemplate.postForObject(ServiceUrl.computeServiceRandom, httpEntity, ResultJson.class);
        return null;
    }
    /*
     *  RestTemplate用法：
        http://www.concretepage.com/spring/spring-mvc/spring-rest-client-resttemplate-consume-restful-web-service-example-xml-json
     */

    public ActionResult GetUsersFail(@RequestParam Integer min, @RequestParam Integer max) {
        return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "服务不可用")), null);
    }
}
