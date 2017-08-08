package com.taimeitech.pass.workflowExt.Listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
@Component
public class TaskEndListener implements ExecutionListener,TaskListener {
    private static final long serialVersionUID = 1L;
    //ExecutionListener类的实现
    public void notify(DelegateExecution execution)   {
        String eventName = execution.getEventName();
//start
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        }else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }
        else if ("take".equals(eventName)) {
            System.out.println("take=========");
        }
    }
    @Override
    public void notify(DelegateTask delegateTask) {
        String approved = (String) delegateTask.getVariable("approved");
        if (approved.equals("true")) {
            Long agreeCounter = (Long) delegateTask.getVariable("approvedBdCounter");
            delegateTask.setVariable("approvedBdCounter", agreeCounter + 1);
        }
    }
}

