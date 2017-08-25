package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/8/9.
 */
public class SaveAuditFormList   implements IReturn<SaveAuditFormListResponse> {

    @ApiModelProperty(value = "表单列表")
    private List<AuditFormDto> data;

    public List<AuditFormDto> getData() {
        return data;
    }

    public void setData(List<AuditFormDto> data) {
        this.data = data;
    }
}
