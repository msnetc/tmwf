package com.taimeitech.pass.workflowExt.taskListener;

import com.sun.source.util.TaskEvent;
import org.activiti.engine.delegate.*;
import org.activiti.engine.task.IdentityLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyAssignmentHandler implements TaskListener {
    private Expression talentId;
    private Expression auditorRoleId;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName =delegateTask.getEventName();
        if(eventName == EVENTNAME_CREATE){
            List<String> users= GetUsers(talentId.getExpressionText(), auditorRoleId.getExpressionText());
            delegateTask.addCandidateUsers(users);
        }
        return;
    }
    private List<String> GetUsers(String talentId, String auditorRoleId){
        List<String> users=new ArrayList<>();
        users.add("u1");
        users.add("u2");
        return users;
    }
}