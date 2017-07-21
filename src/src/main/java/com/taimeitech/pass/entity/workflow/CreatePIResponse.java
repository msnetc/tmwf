package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

public class CreatePIResponse {
    @ApiModelProperty(value = "ProcessInstanceId")
    public String ProcessInstanceId;

    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        ProcessInstanceId = processInstanceId;
    }
}
