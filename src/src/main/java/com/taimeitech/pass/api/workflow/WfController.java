package com.taimeitech.pass.api.workflow;

import com.taimeitech.framework.common.SystemContext;
import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WfController {
    @Autowired
    private WfService wfService;

    @ApiOperation(value = "创建pi，启动工作流", notes = "一般是创建表单后启动任务，把表单对应的id传给工作流引擎")
    @RequestMapping(value = "pi/createPI", method = {RequestMethod.POST})
    public CreatePIResponse Post(@ApiParam("data") @RequestBody CreatePI data) {
        CreatePIResponse response = new CreatePIResponse();
        ProcessInstance pi = wfService.CreatePi(data);
        if (pi != null) {
            response.setData(pi.getId());
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @ApiOperation(value = "获取某个角色/用户的未完成的任务")
    @RequestMapping(value = "task/queryTask", method = {RequestMethod.POST})
    public GetTaskListResponse Post(@ApiParam("data") @RequestBody GetTaskList data) {
        List<Task> tasks = wfService.QueryTasks(data);
        GetTaskListResponse response = new GetTaskListResponse();
        List<tmTask> responseData  = wfService.TasksToTmTasks(tasks);
        response.setData(responseData);
        return response;
    }

    @ApiOperation(value = "撤回任务")
    @RequestMapping(value = "task/rollTack", method = {RequestMethod.POST})
    public RollBackTaskResponse Post(@ApiParam("data") @RequestBody RollBackTask data){
        RollBackTaskResponse response=new RollBackTaskResponse();
        if(StringUtils.isBlank(data.getTaskId())){
            response.setData("taskId不允许为空");
            response.setSuccess(false);
            return response;
        }
        TaskEntity task = wfService.RollBackTask(data.getTaskId());
        tmTask retData = wfService.TaskEntityToTmTasks(task);
        response.setData(retData);
        response.setSuccess(true);
        return response;
    }

    @ApiOperation(value = "完成任务", notes = "某个用户完成分给他的任务")
    @RequestMapping(value = "task/completeTask", method = {RequestMethod.POST})
    public CompleteTaskResponse Post(@ApiParam("data") @RequestBody CompleteTask data) {
        Boolean result = wfService.CompleteTask(data);
        CompleteTaskResponse response = new CompleteTaskResponse();
        response.setSuccess(result);
        return response;
    }

    @ApiOperation(value = "指派任务")
    @RequestMapping(value = "task/assignTask", method = {RequestMethod.POST})
    public AssignTaskResponse Post(@ApiParam("data") @RequestBody AssignTask data){
        AssignTaskResponse response = new AssignTaskResponse();
        Boolean result = wfService.AssignTask(data.getUserId(), data.getTaskId());
        response.setSuccess(result);
        return response;
    }

    @ApiOperation(value = "获取某个角色/用户的历史任务")
    @RequestMapping(value = "historyTask/queryHistoryTask",method = {RequestMethod.POST})
    public GetHistoryTaskResponse Post(@ApiParam("data") @RequestBody GetHistoryTask data) {
        List<HistoricTaskInstance> tasks = wfService.QueryHistoryTasks(data);
        List<HistoryTask> responseData = new ArrayList<>();
        GetHistoryTaskResponse response = new GetHistoryTaskResponse();
        for (HistoricTaskInstance t : tasks) {
            HistoryTask task = new HistoryTask();
            BeanUtils.copyProperties(t, task);
            responseData.add(task);
        }
        response.setData(responseData);
        return  response;
    }

    @ApiOperation(value = "查询历史变量")
    @RequestMapping(value = "History/HistoricVariable", method = {RequestMethod.POST})
    public GetHistoryVariablesResponse Post(@ApiParam("data") @RequestBody GetHistoryVariables data) {
        GetHistoryVariablesResponse response = new GetHistoryVariablesResponse();
        List<HistoricVariableInstance> dataList = wfService.GetHistoryVariables(data);
        List<VariableModel> responseData = new ArrayList<>();
        for (HistoricVariableInstance v:dataList) {
            VariableModel item = new VariableModel();
            item.setCreateTime(v.getCreateTime());
            item.setLastUpdatedTime(v.getLastUpdatedTime());
            item.setProcessInstanceId(v.getProcessInstanceId());
            item.setTaskId(v.getTaskId());
            item.setVariableName(v.getVariableName());
            item.setVariableTypeName(v.getVariableTypeName());
            item.setVariableValue(v.getValue());
            responseData.add(item);
        }
        response.setData(responseData);
        return response;
    }



}