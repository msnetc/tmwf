package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.pass.entity.workflow.CompleteTask;
import com.taimeitech.pass.entity.workflow.CreatePI;
import com.taimeitech.pass.entity.workflow.GetTaskList;
import com.taimeitech.pass.entity.workflow.GetTaskListResponse;
import com.taimeitech.pass.service.workflow.WfService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
        TaskQuery query  = taskService.createTaskQuery();
        if(data.getUserId() !=null){
            query = query.taskCandidateUser(data.getUserId());
        }
        if(data.getProcessInstanceId() !=null){
            query =query.processInstanceId(data.getProcessInstanceId());
        }
        if(data.getTaskName() !=null){
            query =query.taskName(data.getTaskName());
        }
        List<Task> tasks=  query.list();
        return  tasks;
    }

    @Override
    public ProcessInstance CreatePi(CreatePI data) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(data.getProcessDefinitionKey(),data.getBusinessKey(), data.getVariables());
        return pi;
    }

    @Override
    public boolean CompleteTask(CompleteTask data) {
        taskService.claim(data.getTaskId(), data.getUserId());
        taskService.complete(data.getTaskId(), data.getVariables());
         Task task=taskService.createTaskQuery().taskId(data.getTaskId()).singleResult();
        return task==null;
    }
}
