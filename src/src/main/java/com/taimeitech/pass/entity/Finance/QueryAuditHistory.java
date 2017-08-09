package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/9.
 */
public class QueryAuditHistory {
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;


    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
