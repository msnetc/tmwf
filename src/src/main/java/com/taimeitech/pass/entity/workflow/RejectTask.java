package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/10/16.
 */
public class RejectTask implements  IReturn<RejectTaskResponse>{

    @ApiModelProperty(value = "目标taskName")
    private String destTaskKey;

    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;

    @ApiModelProperty(value = "处理人")
    private String destTaskUserId;

    @ApiModelProperty(value = "驳回原因")
    private String rejectMessage;


    public String getDestTaskKey() {
        return destTaskKey;
    }

    public void setDestTaskKey(String destTaskKey) {
        this.destTaskKey = destTaskKey;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public String getDestTaskUserId() {
        return destTaskUserId;
    }

    public void setDestTaskUserId(String destTaskUserId) {
        this.destTaskUserId = destTaskUserId;
    }
}
