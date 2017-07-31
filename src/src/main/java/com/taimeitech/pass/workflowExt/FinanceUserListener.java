package com.taimeitech.pass.workflowExt;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by yanjie.miao on 2017/7/31.
 */
public class FinanceUserListener implements TaskListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approveFinance");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedFinanceCounter");
            delegateTask.setVariable("approvedFinanceCounter", agreeCounter + 1);
        }
    }

}
