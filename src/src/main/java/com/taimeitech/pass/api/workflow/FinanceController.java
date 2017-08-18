package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.Finance.*;
//import com.taimeitech.pass.service.FinanceSystem.AuditFormService;
import com.taimeitech.pass.entity.workflow.CompleteTask;
import com.taimeitech.pass.entity.workflow.CompleteTaskResponse;
import com.taimeitech.pass.entity.workflow.GetTaskListResponse;
import com.taimeitech.pass.entity.workflow.tmTask;
import com.taimeitech.pass.service.FinanceSystem.AuditFormService;
import com.taimeitech.pass.service.FinanceSystem.FinanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

@RestController
public class FinanceController {

    @Autowired
    private AuditFormService auditFormService;

    @Autowired
    private FinanceService financeService;

    @ApiOperation(value = "查询未完成任务", notes = "某个用户未完成任务")
    @RequestMapping(value = "finance/QueryUserTask", method = RequestMethod.POST)
    public QueryUserTaskResponse Post(@ApiParam("query") @RequestBody QueryUserTask query){
        QueryUserTaskResponse response = new QueryUserTaskResponse();
        List<UserTask> tasks = financeService.GetUserTasks(query);
        response.setData(tasks);
        return  response;
    }

    @ApiOperation(value = "查询所有任务", notes = "某个用户完成所有任务")
    @RequestMapping(value = "finance/QueryUserAllTask", method = RequestMethod.POST)
    public QueryUserAllTaskResponse Post(@ApiParam("query") @RequestBody QueryUserAllTask query){
        QueryUserAllTaskResponse response = new QueryUserAllTaskResponse();
        List<UserTask> tasks = financeService.GetUserTasks(query);
        response.setData(tasks);
        return  response;
    }

    @ApiOperation(value = "完成任务", notes = "批量完成任务")
    @RequestMapping(value = "finance/CompleteTasks", method = RequestMethod.POST)
    public CompleteTasksResponse Post(@ApiParam("req") @RequestBody CompleteTasks req) {
        CompleteTasksResponse response = new CompleteTasksResponse();
        Boolean ret = financeService.CompleteTasks(req);
        response.setSuccess(ret);
        return response;
    }

}
