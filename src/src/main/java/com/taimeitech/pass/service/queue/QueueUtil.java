package com.taimeitech.pass.service.queue;

import com.rabbitmq.client.AMQP;
import com.taimeitech.framework.message.RabbitMessageSender;
import com.taimeitech.framework.util.SerializeUtils;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class QueueUtil implements  IQueueUtil{

    @Autowired
    private ConnectionFactory connectionFactory;

    public void declareQueue(String queueName) throws IOException {
        Connection    connection = connectionFactory.createConnection();
        Channel channel = ( Channel) connection.createChannel(false);
        try {
            channel.queueDeclareNoWait(queueName, true, false, false, null);
        } finally {
            RabbitUtils.closeChannel(channel);
            RabbitUtils.closeConnection(connection);
        }
    }

//    private RabbitTemplate getRabbitTemplate(String queueName) throws IOException {
//        if (map.containsKey(queueName)) {
//            return map.get(queueName);
//        }
//        try {
//            lock.lock();
//            if (map.containsKey(queueName)) {
//                return map.get(queueName);
//            }
//            RabbitTemplate template = new RabbitTemplate(connectionFactory);
//            template.setQueue(queueName);
//            template.setRoutingKey(queueName);
//            declareQueue(queueName);
//            map.put(queueName, template);
//            return template;
//        } finally {
//            lock.unlock();
//        }
//    }

//    private void sendMq(String jobLogId, Job job) throws IOException {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", job.getId());
//        map.put("tenantId", job.getTenantId());
//
//        map.put("appId", job.getAppId());
//        map.put("jobCode", job.getJobCode());
//        map.put("jobName", job.getJobName());
//        map.put("jobContext", job.getJobContext());
//        map.put("dataId", job.getDataId());
//        map.put("cronExpression", job.getCronExpression());
//        map.put("periodic", job.getPeriodic());
//        map.put("status", job.getStatus());
//        map.put("jobLogId", jobLogId);
//        String json = SerializeUtils.toJson(map);
//        String queueName = JobConstant.buildQueueName(job.getAppId());
//        RabbitTemplate rabbitTemplate = getRabbitTemplate(queueName);
//        rabbitTemplate.convertAndSend(json);
//    }

}
