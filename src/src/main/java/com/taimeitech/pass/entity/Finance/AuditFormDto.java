package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class AuditFormDto {
    @ApiModelProperty(value = "任务id")
    @Column
    private String taskId;

    @ApiModelProperty(value = "处理人userId")
    @Column
    private String processUserId;

    @ApiModelProperty(value = "处理人姓名")
    @Column
    private String processUserName;

    @ApiModelProperty(value = "审批结果-是否通过")
    @Column
    private boolean approved;

    @ApiModelProperty(value = "审批意见")
    @Column
    private String examinationComment;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessUserId() {
        return processUserId;
    }

    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getExaminationComment() {
        return examinationComment;
    }

    public void setExaminationComment(String examinationComment) {
        this.examinationComment = examinationComment;
    }
}
