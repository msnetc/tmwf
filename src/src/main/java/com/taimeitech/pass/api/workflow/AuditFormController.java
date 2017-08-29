package com.taimeitech.pass.api.workflow;


import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.Finance.AuditFormDto;
import com.taimeitech.pass.entity.Finance.SaveAuditFormList;
import com.taimeitech.pass.entity.Finance.SaveAuditFormListResponse;
import com.taimeitech.pass.entity.workflow.CompleteTask;
import com.taimeitech.pass.entity.workflow.CompleteTaskResponse;
import com.taimeitech.pass.entity.workflow.tmTask;
import com.taimeitech.pass.service.FinanceSystem.AuditFormService;
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

@RestController
public class AuditFormController {
    @Autowired
    private AuditFormService auditFormService;

    @ApiOperation(value = "完成审核单", notes = "完成审核单")
    @RequestMapping(value = "form/SaveAuditFormList", method = RequestMethod.POST)
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
}
