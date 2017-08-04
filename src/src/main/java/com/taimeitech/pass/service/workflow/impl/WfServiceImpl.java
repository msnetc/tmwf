package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

@Service
public class WfServiceImpl implements WfService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Override
    public List<Task> QueryTasks(GetTaskList data) {
        TaskQuery query = taskService.createTaskQuery();
        String userId = data.getUserId();
        if (StringUtil.isNotEmpty(userId)) {
             query=query.taskAssignee(userId);
        }
        if (StringUtils.isNotEmpty(data.getProcessInstanceId())) {
            query = query.processInstanceId(data.getProcessInstanceId());
        }
        if (StringUtils.isNotEmpty(data.getTaskName())) {
            query = query.taskName(data.getTaskName());
        }
        List<Task> tasks = query.list();
        return tasks;
    }

    @Override
    public ProcessInstance CreatePi(CreatePI data) {
        Map<String, Object> variables = data.getVariables();
        UpdateVariables(variables);
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(data.getProcessDefinitionKey(), data.getBusinessKey(), variables);
        return pi;
    }

    @Override
    public boolean CompleteTask(CompleteTask data) {
        taskService.claim(data.getTaskId(), data.getUserId());
        taskService.complete(data.getTaskId(), data.getVariables());
        Task task = taskService.createTaskQuery().taskId(data.getTaskId()).singleResult();
        return task == null;
    }

    @Override
    public List<HistoricTaskInstance> QueryHistoryTasks(GetHistoryTask data){
        HistoricTaskInstanceQuery taskHistoryQuery = processEngine.getHistoryService().createHistoricTaskInstanceQuery(); // 创建历史任务实例查询
        if (StringUtil.isNotEmpty(data.getUserId())) {
            taskHistoryQuery = taskHistoryQuery.taskAssignee(data.getUserId());
        }
        if (StringUtils.isNotEmpty(data.getProcessInstanceId())) {
            taskHistoryQuery = taskHistoryQuery.processInstanceId(data.getProcessInstanceId());
        }
        if (StringUtils.isNotEmpty(data.getTaskName())) {
            taskHistoryQuery = taskHistoryQuery.taskName(data.getTaskName());
        }
        List<HistoricTaskInstance> result = taskHistoryQuery.list();
        return result;
    }

    @Override
    public List<HistoricVariableInstance> GetHistoryVariables(GetHistoryVariables queryParam) {

//创建一个历史的流程变量查询
        HistoricVariableInstanceQuery query = processEngine.getHistoryService().createHistoricVariableInstanceQuery();
        if (StringUtil.isNotEmpty(queryParam.getVaribleName())) {
            query = query.variableNameLike(queryParam.getVaribleName());
        }
        if (StringUtils.isNotEmpty(queryParam.getTaskId())) {
            query = query.taskId(queryParam.getTaskId());
        }
        if (StringUtils.isNotEmpty(queryParam.getProcessInstanceId())) {
            query = query.processInstanceId(queryParam.getProcessInstanceId());
        }
        List<HistoricVariableInstance> list = query.list();
        return list;
    }

    private List<User> GetUsers(String groupId){
        UserQuery query = identityService.createUserQuery();
        if(StringUtil.isNotEmpty(groupId)){
            query=query.memberOfGroup(groupId.trim());
        }
        List<User> users = query.list();
        return users;
    }

    private List<String> GetUserIds(String groupId){
        UserQuery query = identityService.createUserQuery();
        if(StringUtil.isNotEmpty(groupId)){
            query=query.memberOfGroup(groupId.trim());
        }
        List<User> users = query.list();
        List<String> userIds = new ArrayList<String>();
        for (User u:users) {
            userIds.add(u.getId());
        }
        return userIds;
    }

    private void UpdateVariables( Map<String, Object> variables){
         if(variables!=null){
            for (Map.Entry<String, Object> entry:variables.entrySet()) {
                if(entry.getKey().startsWith("group_")){
                    String groupName = StringUtils.substringAfter(entry.getKey(), "group_");
                    List<String> userIds=GetUserIds(groupName);
                    variables.replace(entry.getKey(), userIds);
                }
            }
        }

    }

}
