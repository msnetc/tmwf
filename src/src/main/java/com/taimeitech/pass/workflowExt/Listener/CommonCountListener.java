package com.taimeitech.pass.workflowExt.Listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by yanjie.miao on 2017/9/22.
 */
public class CommonCountListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("totalApprovedCount");
            delegateTask.setVariable("totalApprovedCount", agreeCounter + 1);
        }
    }
}
