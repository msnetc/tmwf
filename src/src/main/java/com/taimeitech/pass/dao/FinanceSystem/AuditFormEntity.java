package com.taimeitech.pass.dao.FinanceSystem;


import com.taimeitech.framework.common.po.BasePO;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "AuditForm")
public class AuditFormEntity  extends BasePO {

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

    @ApiModelProperty(value = "处理日期")
    @Column
    private DateTime processDate;

    @ApiModelProperty(value = "任务日期")
    @Column
    private DateTime taskAssignDate;

    @ApiModelProperty(value = "审批结果-是否通过")
    @Column
    private boolean Approved;

    @ApiModelProperty(value = "审批意见")
    @Column
    private String ExaminationComment;


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

    public DateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(DateTime processDate) {
        this.processDate = processDate;
    }

    public DateTime getTaskAssignDate() {
        return taskAssignDate;
    }

    public void setTaskAssignDate(DateTime taskAssignDate) {
        this.taskAssignDate = taskAssignDate;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
    }

    public String getExaminationComment() {
        return ExaminationComment;
    }

    public void setExaminationComment(String examinationComment) {
        ExaminationComment = examinationComment;
    }
}
