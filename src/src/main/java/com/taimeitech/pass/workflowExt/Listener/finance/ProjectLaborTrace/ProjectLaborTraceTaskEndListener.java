package com.taimeitech.pass.workflowExt.Listener.finance.ProjectLaborTrace;

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

/**
 * Created by yanjie.miao on 2017/8/23.
 */
public class ProjectLaborTraceTaskEndListener  implements ExecutionListener,TaskListener {

    public void notify(DelegateExecution execution) {
        String processInstanceId = execution.getProcessInstanceId();
        String pdId = GetPdId(execution.getProcessDefinitionId());
        SendMsg(pdId, processInstanceId, true);
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
    }

    private String GetPdId(String processInstanceId) {
        String ret = processInstanceId.substring(0, processInstanceId.indexOf(":"));
        return ret;
    }

    private void SendMsg(String processId, String piId, boolean reslult) {
        try {
            getQueueUtil().declareQueue(processId);
            Map<String, Object> map = new HashMap<>();
            map.put("ProcessInstanceId", piId);
            map.put("IsPass", reslult);
            String messageData = SerializeUtils.toJson(map);

            //getRabbitMessageSender().dynamicSend("",processId, messageData);
            getRabbitMessageSender().directSend(processId, messageData);
        } catch (Exception ex) {
            TaimeiLogger.error(ex);
        }
    }

    public RabbitMessageSender getRabbitMessageSender() {
        return SpringUtils.getBean(RabbitMessageSender.class);
    }

    public IQueueUtil getQueueUtil() {
        return SpringUtils.getBean(IQueueUtil.class);
    }
}
