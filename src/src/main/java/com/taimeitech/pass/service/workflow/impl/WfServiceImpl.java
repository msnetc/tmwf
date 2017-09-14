package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.Finance.CompleteTasks;
import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
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

        if (StringUtil.isNotBlank(userId)) {
             query=query.taskCandidateOrAssigned(userId);
        }
        if (StringUtils.isNotBlank(data.getProcessInstanceId())) {
            query = query.processInstanceId(data.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(data.getTaskId())) {
            query = query.taskId(data.getTaskId());
        }
        List<Task> tasks = query.list();
        return tasks;
    }

    @Override
    public ProcessInstance CreatePi(CreatePI data) {
        Map<String, Object> variables = data.getVariables();
        if(StringUtils.isNotBlank(data.getBusinessKey())){
            ProcessInstanceQuery piQuery = runtimeService.createProcessInstanceQuery();
            List<ProcessInstance> processInstances = piQuery.processInstanceBusinessKey(data.getBusinessKey()).list();
        }
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(data.getProcessDefinitionKey(), data.getBusinessKey(), variables);
        return pi;
    }

    @Override
    public boolean CompleteTask(CompleteTask data) {
        taskService.setVariablesLocal(data.getTaskId(),data.getVariables());
        taskService.claim(data.getTaskId(), data.getUserId());
        taskService.complete(data.getTaskId(), data.getVariables());
        return true;
    }

    @Override
    public List<HistoricTaskInstance> QueryHistoryTasks(GetHistoryTask data){
        HistoricTaskInstanceQuery taskHistoryQuery = processEngine.getHistoryService().createHistoricTaskInstanceQuery(); // 创建历史任务实例查询
        if (StringUtil.isNotBlank(data.getUserId())) {
            taskHistoryQuery = taskHistoryQuery.taskAssignee(data.getUserId());
        }
        if (StringUtils.isNotBlank(data.getProcessInstanceId())) {
            taskHistoryQuery = taskHistoryQuery.processInstanceId(data.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(data.getTaskName())) {
            taskHistoryQuery = taskHistoryQuery.taskName(data.getTaskName());
        }
        List<HistoricTaskInstance> result = taskHistoryQuery.list();
        return result;
    }

    @Override
    public List<HistoricVariableInstance> GetHistoryVariables(GetHistoryVariables queryParam) {

//创建一个历史的流程变量查询
        HistoricVariableInstanceQuery query = processEngine.getHistoryService().createHistoricVariableInstanceQuery();
        if (StringUtil.isNotBlank(queryParam.getVaribleName())) {
            query = query.variableNameLike(queryParam.getVaribleName());
        }
        if (StringUtils.isNotBlank(queryParam.getTaskId())) {
            query = query.taskId(queryParam.getTaskId());
        }
        if (StringUtils.isNotBlank(queryParam.getProcessInstanceId())) {
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



}
