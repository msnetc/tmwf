package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class AuditFormDto {
    @ApiModelProperty(value = "processInstanceId")
    @Column
    private String processInstanceId;

    @ApiModelProperty(value = "任务id")
    @Column
    private int taskId;

    @ApiModelProperty(value = "节点名称")
    @Column
    private String taskName;

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

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
