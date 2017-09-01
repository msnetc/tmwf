package com.taimeitech.pass.api.workflow;


import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.approval.*;
import com.taimeitech.pass.service.FinanceSystem.AuditFormService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuditFormController {
    @Autowired
    private AuditFormService auditFormService;

    @ApiOperation(value = "完成审核单", notes = "完成审核单")
    @RequestMapping(value = "approval/SaveAuditFormList", method = RequestMethod.POST)
    public SaveAuditFormListResponse Post(@ApiParam("request") @RequestBody SaveAuditFormList request){
        SaveAuditFormListResponse response = new SaveAuditFormListResponse();
        List<AuditFormEntity> data = new ArrayList<>();
        for (AuditFormDto t : request.getData()) {
            AuditFormEntity form = new AuditFormEntity();
            BeanUtils.copyProperties(t, form);
            data.add(form);
        }
        Boolean ret = auditFormService.SaveAuditFormList(data);
        response.setSuccess(ret);
        return  response;
    }

    @ApiOperation(value = "获取用户的审核信息", notes = "完成审核单")
    @RequestMapping(value = "approval/GetAuditFormList", method = RequestMethod.POST)
    public GetAllUserTaskResponse Post(@ApiParam("request") @RequestBody GetAllUserTask request){
        GetAllUserTaskResponse response = new GetAllUserTaskResponse();
        return response;
    }

}
