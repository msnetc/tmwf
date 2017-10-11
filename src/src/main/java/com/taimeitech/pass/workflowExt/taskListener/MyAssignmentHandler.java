package com.taimeitech.pass.workflowExt.taskListener;



import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import java.util.ArrayList;
import java.util.List;


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