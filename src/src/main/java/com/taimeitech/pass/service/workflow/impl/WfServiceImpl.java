package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WfServiceImpl implements WfService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public List<Task> QueryTasks(GetTaskList data) {
        TaskQuery query = taskService.createTaskQuery();

        if (StringUtil.isNotEmpty(data.getUserId())) {
            query = query.taskCandidateUser(data.getUserId());
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
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(data.getProcessDefinitionKey(), data.getBusinessKey(), data.getVariables());
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
            taskHistoryQuery = taskHistoryQuery.taskCandidateUser(data.getUserId());
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
    public List<HistoricVariableInstance> GetHistoryVariables() {
        return null;
    }

}
