package com.taimeitech.pass.service.micrservice;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.entity.RangeRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zhengjun.zhang on 2017/6/9.
 */
@Service
public class ServiceProviderService {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String appPort;

    public ActionResult getRangeRandom(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        RangeRandom rangeRandom = new RangeRandom(min, max, randomNum, min + "和" + max + "之间的随机数是：" + randomNum);

        return new ActionResult(true, null, rangeRandom);
    }
}
