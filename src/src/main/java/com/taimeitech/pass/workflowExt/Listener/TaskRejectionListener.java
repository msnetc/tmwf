package com.taimeitech.pass.workflowExt.Listener;
import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.message.RabbitMessageSender;
import com.taimeitech.framework.util.SerializeUtils;
import com.taimeitech.pass.SpringUtils;
import com.taimeitech.pass.service.queue.IQueueUtil;
import com.taimeitech.pass.service.queue.QueueUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class TaskRejectionListener implements ExecutionListener,TaskListener {
    public RabbitMessageSender getRabbitMessageSender() {
        return SpringUtils.getBean(RabbitMessageSender.class);
    }
    public IQueueUtil getQueueUtil() {
        return SpringUtils.getBean(IQueueUtil.class);
    }

    @Override
    public void notify(DelegateTask delegateTask) {

    }

    //ExecutionListener类的实现
    public void notify(DelegateExecution execution)   {
        SendMsg(execution.getProcessDefinitionId(), false);
    }

    private void SendMsg(String processId, boolean reslult){
        try{
            getQueueUtil().declareQueue(processId);
            Map<String, Object> map = new HashMap<>();
            map.put("ProcessInstanceId", processId);
            map.put("IsPass", reslult);
            String messageData = SerializeUtils.toJson(map);
            getRabbitMessageSender().directSend(processId, messageData);
        }
        catch (Exception ex){
            TaimeiLogger.error(ex);
        }
    }
}
