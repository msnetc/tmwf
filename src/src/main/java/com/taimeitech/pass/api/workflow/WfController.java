package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
    WfService wfService;

    @ApiOperation(value = "创建pi，启动工作流", notes = "一般是创建表单后启动任务，把表单对应的id传给工作流引擎")
    @RequestMapping(value = "pi/createPI", method = RequestMethod.POST)
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
    @RequestMapping(value = "task/queryTask", method = RequestMethod.POST)
    public GetTaskListResponse Post(@ApiParam("data") @RequestBody GetTaskList data) {
        List<Task> tasks = wfService.QueryTasks(data);
        List<tmTask> responseData = new ArrayList<tmTask>();
        GetTaskListResponse response = new GetTaskListResponse();
        for (Task t : tasks) {
            tmTask task = new tmTask();
            BeanUtils.copyProperties(t, task);
            task.setTaskId(t.getId());
            responseData.add(task);
        }
        response.setData(responseData);
        return response;
    }

    @ApiOperation(value = "完成任务", notes = "某个用户完成分给他的任务")
    @RequestMapping(value = "task/completeTask", method = RequestMethod.POST)
    public CompleteTaskResponse Post(@ApiParam("data") @RequestBody CompleteTask data) {
        Boolean result = wfService.CompleteTask(data);
        CompleteTaskResponse response = new CompleteTaskResponse();
        response.setSuccess(result);
        return response;
    }

    @ApiOperation(value = "获取某个角色/用户的历史任务")
    @RequestMapping(value = "historyTask/queryHistoryTask", method = RequestMethod.POST)
    public GetHistoryTaskResponse Post(@ApiParam("data") @RequestBody GetHistoryTask data) {
        List<HistoricTaskInstance> tasks = wfService.QueryHistoryTasks(data);
        List<tmTask> responseData = new ArrayList<tmTask>();
        GetTaskListResponse response = new GetTaskListResponse();
        for (HistoricTaskInstance t : tasks) {
            tmTask task = new tmTask();
            BeanUtils.copyProperties(t, task);
            responseData.add(task);
        }
        return new GetHistoryTaskResponse();
    }

    @ApiOperation(value = "工作流回调接口")
    @RequestMapping(value = "pi/WorkFlowCallBack", method = RequestMethod.POST)
    public WorkFlowCallBackResponse Post(@ApiParam("data") @RequestBody WorkFlowCallBack data) {
        WorkFlowCallBackResponse response = new WorkFlowCallBackResponse();
        response.setSuccess(true);
        return response;
    }
}



