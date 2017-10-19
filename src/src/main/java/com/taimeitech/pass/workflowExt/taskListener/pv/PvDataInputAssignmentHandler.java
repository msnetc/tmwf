package com.taimeitech.pass.workflowExt.taskListener.pv;

import com.taimeitech.pass.SpringUtils;
import com.taimeitech.pass.entity.member.UserRole;
import com.taimeitech.pass.entity.member.UserRolesResult;
import com.taimeitech.pass.entity.workflow.tmTaskUser;
import com.taimeitech.pass.service.member.UserService;
import com.taimeitech.pass.service.queue.IQueueUtil;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import java.util.ArrayList;
import java.util.List;

public class PvDataInputAssignmentHandler implements TaskListener {
    private Expression talentId;
    private Expression softId;
    private Expression currentTaskRoleId;
    private  Expression QualityCheck_RoleId;
    private  Expression MedicineCheck_RoleId;
    private  Expression Recheck_RoleId;
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
                List<String> candidateUsers= GetCurrentCandidateUsers();
                delegateTask.addCandidateUsers(candidateUsers);
            }
            List<tmTaskUser> nextUsers= GetNextTaskCandidateUsers();
            delegateTask.setVariableLocal("nextTaskUsers", nextUsers);
        }
        return;
    }

    // 获取当前任务候选用户列表
    private List<String> GetCurrentCandidateUsers(){
        UserService userService = getUserService();
        String strTalentId=getTalentId();
        String strSoftId =getSoftId();
        String roleId=getCurrentTaskRoleId();
        UserRolesResult users = userService.GetUsers(strTalentId, strSoftId,roleId);
        List<String> ret=new ArrayList<>();
        for(UserRole ur : users.getData().getData()) {
            ret.add(ur.getId());
        }
        return ret;
    }

    public UserService getUserService() {
        return SpringUtils.getBean(UserService.class);
    }

    private String getTalentId(){
        return talentId.getExpressionText();
    }

    private String getSoftId(){
        return softId.getExpressionText();
    }
    private String getCurrentTaskRoleId(){
        return currentTaskRoleId.getExpressionText();
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