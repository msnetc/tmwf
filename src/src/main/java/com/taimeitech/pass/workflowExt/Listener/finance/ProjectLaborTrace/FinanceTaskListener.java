package com.taimeitech.pass.workflowExt.Listener.finance.ProjectLaborTrace;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by yanjie.miao on 2017/8/23.
 */
public class FinanceTaskListener implements TaskListener {
    private static final long serialVersionUID = 1L;
    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedFinanceUsersCounter");
            delegateTask.setVariable("approvedFinanceUsersCounter", agreeCounter + 1);
        }
    }
}
