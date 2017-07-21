package com.taimeitech.pass.api.micrservice;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.service.micrservice.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by zhengjun.zhang on 2017/6/9.
 * 微服务提供者，只需要对外提供 Restful API 即可，和常规的开发一致
 */
@RestController
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @RequestMapping("/getRangeRandom")
    public ActionResult getRangeRandom(@RequestParam int min, @RequestParam int max) {
        if (min > max) {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-2, "区间有误，最小值不能大于最大值")), null);
        }

        return serviceProviderService.getRangeRandom(min, max);
    }
}
