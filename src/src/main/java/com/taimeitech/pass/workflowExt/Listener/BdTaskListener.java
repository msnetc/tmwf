package com.taimeitech.pass.workflowExt.Listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class BdTaskListener implements TaskListener {
    private static final long serialVersionUID = 1L;
    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedBdCounter");
            delegateTask.setVariable("approvedBdCounter", agreeCounter + 1);
        }
    }
}
