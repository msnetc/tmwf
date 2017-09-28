package com.taimeitech.pass.workflowExt.Listener.PiEndListener;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.message.RabbitMessageSender;
import com.taimeitech.framework.util.SerializeUtils;
import com.taimeitech.pass.SpringUtils;
import com.taimeitech.pass.service.queue.IQueueUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

import java.util.HashMap;
import java.util.Map;

public class PiEndBase implements ExecutionListener,TaskListener {

    public void notify(DelegateExecution execution)   {
        String processInstanceId =execution.getProcessInstanceId();
        String pdId =GetPdId(execution.getProcessDefinitionId());
        SendMsg(pdId, processInstanceId, getEndEnum());
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
    }

    private String GetPdId(String processInstanceId){
        String ret = processInstanceId.substring(0, processInstanceId.indexOf(":"));
        return ret;
    }

    private void SendMsg(String processId, String piId, PiEndEnum endEnum){
        try{
            getQueueUtil().declareQueue(processId);
            Map<String, Object> map = new HashMap<>();
            map.put("ProcessInstanceId", piId);
            map.put("EndStatus", endEnum.toString());
            String messageData = SerializeUtils.toJson(map);
            getRabbitMessageSender().directSend(processId, messageData);
            TaimeiLogger.warn(messageData);
        }
        catch (Exception ex){
            TaimeiLogger.error(ex);
        }
    }

    public RabbitMessageSender getRabbitMessageSender() {
        return SpringUtils.getBean(RabbitMessageSender.class);
    }

    public IQueueUtil getQueueUtil() {
        return SpringUtils.getBean(IQueueUtil.class);
    }


    private PiEndEnum endEnum;

    public PiEndEnum getEndEnum() {
        return endEnum;
    }

    public void setEndEnum(PiEndEnum endEnum) {
        this.endEnum = endEnum;
    }
}