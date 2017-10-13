package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.workflow.GetTaskList;
import com.taimeitech.pass.entity.workflow.GetTaskListResponse;
import com.taimeitech.pass.entity.workflow.tmTask;
import com.taimeitech.pass.service.workflow.WfService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PvController {
    @Autowired
    private WfService wfService;

    @ApiOperation(value = "获取某个角色/用户的未完成的任务")
    @RequestMapping(value = "pv/task/queryTask", method = {RequestMethod.POST})
    public GetTaskListResponse Post(@ApiParam("data") @RequestBody GetTaskList data) {
        List<Task> tasks = wfService.QueryTasks(data);
        GetTaskListResponse response = new GetTaskListResponse();
        List<tmTask> responseData  = wfService.TasksToTmTasks(tasks, true);
        response.setData(responseData);
        return response;
    }
}
