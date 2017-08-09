package com.taimeitech.pass.dao.FinanceSystem;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

//AuditForm
@Entity(name = "AuditForm")
public class AuditFormEntity implements Serializable {

    private Long id;

    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;
    @ApiModelProperty(value = "任务id")
    private int taskId;
    @ApiModelProperty(value = "节点名称")
    private String taskName;
    @ApiModelProperty(value = "处理人userId")
    private String processUserId;

    @ApiModelProperty(value = "处理人姓名")
    private String processUserName;

    @ApiModelProperty(value = "处理日期")
    private DateTime processDate;
    @ApiModelProperty(value = "任务日期")
    private DateTime taskAssignDate;
    @ApiModelProperty(value = "审批结果-是否通过")
    private boolean Approved;
    @ApiModelProperty(value = "审批意见")
    private String ExaminationComment;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
