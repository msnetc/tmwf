package com.taimeitech.pass.api.workflow;


import com.taimeitech.pass.entity.Finance.QueryAuditHistory;
import com.taimeitech.pass.entity.Finance.QueryAuditHistoryResponse;
import com.taimeitech.pass.entity.Finance.SaveAuditForm;
import com.taimeitech.pass.entity.Finance.SaveAuditFormResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
public class FinanceController {

    @ApiOperation(value = "财务系统/提交审批单")
    @RequestMapping(value = "finance/SaveAuditForm", method = RequestMethod.POST)
    public SaveAuditFormResponse Post(SaveAuditForm data){
        SaveAuditFormResponse response = new SaveAuditFormResponse();
        return response;
    }

    @ApiOperation(value = "财务系统/查询审核经过")
    @RequestMapping(value = "finance/QueryAuditHistory", method = RequestMethod.POST)
    public QueryAuditHistoryResponse Post(QueryAuditHistory data){
        QueryAuditHistoryResponse response = new QueryAuditHistoryResponse();
        return response;
    }

}
