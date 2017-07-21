package com.taimeitech.pass.service.micrservice;

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
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * Created by zhengjun.zhang on 2017/6/9.
 */
//访问远程Service
@Service
public class ServiceConsumerService {
    @Value("${microService.baseUrl}")
    private String baseUrl;

    @Autowired
    private TaimeiRestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getRandomFail")
    public ActionResult getRangeRandom(@RequestParam Integer min, @RequestParam Integer max) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("min", min);
        map.add("max", max);

        HttpHeaders headers = new HttpHeaders();
        //headers.set("token","xxxxx");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);

        String reqUrl = baseUrl + ServiceUrl.computeServiceRandom + "?min=" + min + "&max=" + max;

        //Get方法1
        return restTemplate.exchange(reqUrl, HttpMethod.GET, httpEntity, ActionResult.class).getBody();
        //Get方法2
        //return restTemplate.getForObject(reqUrl,ResultJson.class);
        //Post方法
        // return restTemplate.postForObject(ServiceUrl.computeServiceRandom, httpEntity, ResultJson.class);
    }
    /*
     *  RestTemplate用法：
        http://www.concretepage.com/spring/spring-mvc/spring-rest-client-resttemplate-consume-restful-web-service-example-xml-json#exchange
     */

    public ActionResult getRandomFail(@RequestParam Integer min, @RequestParam Integer max) {
        return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "服务不可用")), null);
    }

}
