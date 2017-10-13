package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import com.taimeitech.pass.workflowExt.JumpCmd;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service

public class WfServiceImpl implements WfService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryServivce;

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
    @Transactional
    public ProcessInstance CreatePi(CreatePI data) {
        Map<String, Object> variables = data.getVariables();
        if(StringUtils.isNotBlank(data.getBusinessKey())){
//            ProcessInstanceQuery piQuery = runtimeService.createProcessInstanceQuery();
//            List<ProcessInstance> processInstances = piQuery.processInstanceBusinessKey(data.getBusinessKey()).list();
        }
        ProcessInstance pi = runtimeService.startProcessInstanceByKeyAndTenantId(data.getProcessDefinitionKey(), data.getBusinessKey(), variables, data.getTenantId());
        return pi;
    }

    @Override
    @Transactional
    public boolean CompleteTask(CompleteTask data) {
        if(data.getVariables().size() >0 ){
            taskService.setVariablesLocal(data.getTaskId(),data.getVariables());
        }
        taskService.complete(data.getTaskId(), data.getVariables());
        return true;
    }

    @Override
    @Transactional
    public boolean AssignTask(String userId, String taskId) {
        taskService.unclaim(taskId);
        taskService.setAssignee(taskId, userId);
        return true;
    }

    @Override
    @Transactional
    public  TaskEntity RollBackTask(String taskId) {
        //根据要跳转的任务ID获取其任务
        HistoricTaskInstance hisTask = historyService
                .createHistoricTaskInstanceQuery().taskId(taskId)
                .singleResult();
        //进而获取流程实例
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(hisTask.getProcessInstanceId())
                .singleResult();

        //取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) repositoryServivce.getProcessDefinition(hisTask.getProcessDefinitionId());
        //获取历史任务的Activity
        ActivityImpl hisActivity = definition.findActivity(hisTask.getTaskDefinitionKey());
        //实现跳转
        ExecutionEntity ee = managementService.executeCommand(new JumpCmd(instance.getId(), hisActivity.getId()));
        List<TaskEntity> tasks = ee.getTasks();
        List<TaskEntity>  ret= new ArrayList<>();
        for (TaskEntity t : tasks) {
            if(t.isDeleted()) continue;
            ret.add(t);
        }
        return ret.get(0);
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

    @Override
    public List<tmTask> TasksToTmTasks(List<Task> tasks){
        List<tmTask> ret = new ArrayList<tmTask>();
        if(tasks==null && tasks.size()==0) return ret;
        for(Task t:tasks) {
            tmTask item = new tmTask();
            BeanUtils.copyProperties(t, item);
            item.setTaskId(t.getId());
            item.setAssignee(t.getAssignee());
            Map<String, Object> variables =taskService.getVariables(t.getId());
            Map<String, Object> variables2=  taskService.getVariablesLocal(t.getId());
            TaimeiLogger.info(variables2);
            item.setProcessVariables(variables);
            ret.add(item);
        }
        return ret;
    }

    @Override
    public tmTask TaskEntityToTmTasks(TaskEntity t){
        if(t==null) return null;
        tmTask item = new tmTask();
        BeanUtils.copyProperties(t, item);
        item.setTaskId(t.getId());
        item.setAssignee(t.getAssignee());
        return item;
    }

}
