package com.taimeitech.pass.workflowExt.Listener.finance;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class LaborHourTraceAuditCountListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedAuditiCounter");
            delegateTask.setVariable("approvedAuditiCounter", agreeCounter + 1);
        }
    }
}
