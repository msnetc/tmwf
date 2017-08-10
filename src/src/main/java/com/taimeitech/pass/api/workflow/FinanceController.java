package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.Finance.*;
//import com.taimeitech.pass.service.FinanceSystem.AuditFormService;
import com.taimeitech.pass.entity.workflow.GetTaskListResponse;
import com.taimeitech.pass.entity.workflow.tmTask;
import com.taimeitech.pass.service.FinanceSystem.FinanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

@RestController
public class FinanceController {

    @Autowired
    private FinanceService financeService;


    //    @Autowired
    ////    private  AuditFormService auditFormService;
    ////
    ////    @ApiOperation(value = "财务系统/提交审批单")
    ////    @RequestMapping(value = "finance/SaveAuditForm", method = RequestMethod.POST)
    ////    public SaveAuditFormResponse Post(SaveAuditForm data){
    ////        SaveAuditFormResponse response = new SaveAuditFormResponse();
    ////        if(data.data !=null && data.data.isEmpty()){
    ////        }
    ////        return response;
    ////    }


//    @ApiOperation(value = "查询审核经过")
//    @RequestMapping(value = "finance/QueryAuditHistory", method = RequestMethod.POST)
//    public QueryAuditHistoryResponse Post(@ApiParam("query") @RequestBody QueryAuditHistory query){
//        String id =query.getProcessInstanceId();
//        QueryAuditHistoryResponse response = new QueryAuditHistoryResponse();
//        return response;
//    }


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

}
