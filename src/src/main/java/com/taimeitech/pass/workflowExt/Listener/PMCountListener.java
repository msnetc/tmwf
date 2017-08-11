package com.taimeitech.pass.workflowExt.Listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by yanjie.miao on 2017/8/11.
 */
public class PMCountListener  implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedPMCounter");
            delegateTask.setVariable("approvedPMCounter", agreeCounter + 1);
        }
    }

}
