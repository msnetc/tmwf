package com.taimeitech.pass.workflowExt.Listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;


@Component
public class LawWorkerListener implements TaskListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approvedLaw");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedLawCounter");
            delegateTask.setVariable("approvedLawCounter", agreeCounter + 1);
        }
    }

}
