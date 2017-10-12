package com.taimeitech.pass.workflowExt.taskListener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import java.util.ArrayList;
import java.util.List;

public class PvAssignmentHandler implements TaskListener {
    private Expression talentId;
    private Expression softId;
    private Expression currentTaskRoleId;
    private Expression nextTaskRoleId;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName =delegateTask.getEventName();
        if(eventName == EVENTNAME_CREATE){
            List<String> users= GetCurrentCandidateUsers();
            delegateTask.addCandidateUsers(users);
            delegateTask.setVariableLocal("nextTaskUsers", GetNextTaskCandidateUsers());
        }
        return;
    }

    private List<String> GetCurrentCandidateUsers(){
        List<String> users=new ArrayList<>();
        users.add("c1");
        users.add("c2");
        users.add("c3");
        return users;
    }

    private List<String> GetNextTaskCandidateUsers(){
        List<String> users=new ArrayList<>();
        users.add("n1");
        users.add("n2");
        users.add("n3");
        return users;
    }

}