package com.taimeitech.pass.api.micrservice;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.service.micrservice.ServiceConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengjun.zhang on 2017/6/9.
 */
@RestController
public class ServiceConsumerController {
//    @Autowired
//    private TaimeiRestTemplate restTemplate;

    @Autowired
    private ServiceConsumerService serviceConsumerService;

    @GetMapping(value = "/rangeRandom")
    public ActionResult getRangeRandom(@RequestParam int min, @RequestParam int max) {
        TaimeiLogger.info("test");
        return serviceConsumerService.getRangeRandom(min, max);
    }

//    @GetMapping(value = "/testBalance")
//    public String testBalance(@RequestParam String srcText, @RequestParam String findText) {
//        String reqUrl = "http://count-service/getContainCount?srcText=" + srcText + "&findText=" + findText;
//        return restTemplate.getForEntity(reqUrl, String.class).getBody();
//    }
}
