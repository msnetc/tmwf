package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

public class WorkFlowCallBack implements IReturn<WorkFlowCallBackResponse> {
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;

    @ApiModelProperty(value = "是否成功")
    private boolean hasSucess;

    public boolean getHasSucess() {
        return hasSucess;
    }

    public void setHasSucess(boolean hasSucess) {
        this.hasSucess = hasSucess;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

}
