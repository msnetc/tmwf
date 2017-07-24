package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.workflow.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WfController {

    @ApiOperation(value = "创建pi，启动工作流",notes="一般是创建表单后启动任务，把表单对应的id传给工作流引擎")
    @RequestMapping(value = "pi/createPI", method = RequestMethod.POST)
    public CreatePIResponse Post(@ApiParam("pi") @RequestBody CreatePI pi) {
        return new CreatePIResponse();
    }

    @ApiOperation(value = "获取某个角色/用户的未完成的任务")
    @RequestMapping(value = "task/queryTask", method = RequestMethod.POST)
    public GetTaskListResponse Post(@ApiParam("queryTask") @RequestBody GetTaskList queryTask) {
        return new GetTaskListResponse();
    }

    @ApiOperation(value = "完成任务",notes="某个用户完成分给他的任务")
    @RequestMapping(value = "task/completeTask", method = RequestMethod.POST)
    public CompleteTaskResponse Post(@ApiParam("completeTask") @RequestBody CompleteTask completeTask) {
        return new CompleteTaskResponse();
    }

    @ApiOperation(value = "获取某个角色/用户的历史任务")
    @RequestMapping(value = "historyTask/queryHistoryTask", method = RequestMethod.POST)
    public GetHistoryTaskResponse Post(@ApiParam("completeTask") @RequestBody GetHistoryTask completeTask) {
        return new GetHistoryTaskResponse();
    }

}



