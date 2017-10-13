package com.taimeitech.pass.workflowExt.taskListener.pv;

import com.taimeitech.pass.entity.workflow.tmTaskUser;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PvDataInputAssignmentHandler implements TaskListener {
    private Expression talentId;
    private Expression softId;
    private Expression currentTaskRoleId;
    private Expression nextTaskRoleId;

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName =delegateTask.getEventName();
        if(eventName == EVENTNAME_CREATE){
            Object dataInputUserId = delegateTask.getVariable("dataInputUserId");
            if(dataInputUserId != null){
                String strDataInputUserId = dataInputUserId.toString();
                delegateTask.setAssignee(strDataInputUserId);
            }
            else{
                List<String> candidateUsers= GetCurrentCandidateUsers(delegateTask);
                delegateTask.addCandidateUsers(candidateUsers);
            }
            List<tmTaskUser> nextUsers= GetNextTaskCandidateUsers();
            delegateTask.setVariableLocal("nextTaskUsers", nextUsers);
        }
        return;
    }
    // 获取当前任务候选用户列表
    private List<String> GetCurrentCandidateUsers(DelegateTask delegateTask){
        List<String> users=new ArrayList<>();
        users.add("c1");
        users.add("c2");
        users.add("c3");
        return users;
    }
    // 获取下个任务的用户列表
    private List<tmTaskUser> GetNextTaskCandidateUsers(){
        List<tmTaskUser> users=new ArrayList<>();
        users.add(new tmTaskUser("u1","name1"));
        users.add(new tmTaskUser("u2","name2"));
        users.add(new tmTaskUser("u3","name3"));
        return users;
    }

}