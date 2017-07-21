package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

public class CreatePIResponse {
    @ApiModelProperty(value = "processInstanceId")
    public String processInstanceId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
