package com.taimeitech.pass.service.queue;

import java.io.IOException;

/**
 * Created by yanjie.miao on 2017/8/8.
 */
public interface IQueueUtil {
    void declareQueue(String queueName) throws IOException;
}
