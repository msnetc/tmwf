package com.taimeitech.pass.workflowExt.taskListener.pv;

import com.taimeitech.pass.SpringUtils;
import com.taimeitech.pass.workflowExt.taskListener.JDJumpTaskCmd;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PvTaskDelete implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        Object approved =  delegateTask.getVariable("approved");
       if (approved != null && approved.toString().equals("false")) {
            JumpTask(delegateTask);
        }
        else{
              List<String> activityIds = getRuntimeService().getActiveActivityIds(delegateTask.getExecutionId());
              String previousActivityId = activityIds.get(0);
              delegateTask.setVariable("previousActivityId", previousActivityId);
              delegateTask.setVariable("previousTaskUserId",delegateTask.getAssignee());
          }
    }


    private void JumpTask(DelegateTask delegateTask){
        Object taskCompleteUserId = delegateTask.getVariable("previousTaskUserId").toString();
        String strTaskCompleteUserId = taskCompleteUserId.toString();
        String previousActivityId = (String) delegateTask.getVariable("previousActivityId");
        String pdId = delegateTask.getProcessDefinitionId();
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) getRepositoryService().getProcessDefinition(pdId);
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity(previousActivityId);

        List<String> currentActivitiIds = getRuntimeService().getActiveActivityIds(delegateTask.getExecutionId());
        String currentActivitiId = currentActivitiIds.get(0);
        ActivityImpl currentActivity = (ActivityImpl)processDefinitionEntity.findActivity(currentActivitiId);

        Map<String, Object> vars = new HashMap<String, Object>();
        String[] v = { strTaskCompleteUserId };
        vars.put("assigneeList", Arrays.asList(v));
        getManagementService().executeCommand(new JDJumpTaskCmd(delegateTask.getExecutionId(), destinationActivity,vars, currentActivity));

    }

    private ManagementService getManagementService() {
        return SpringUtils.getBean(ManagementService.class);
    }

    private RepositoryService getRepositoryService() {
        return SpringUtils.getBean(RepositoryService.class);
    }

    private RuntimeService getRuntimeService() {
        return SpringUtils.getBean(RuntimeService.class);
    }


}
