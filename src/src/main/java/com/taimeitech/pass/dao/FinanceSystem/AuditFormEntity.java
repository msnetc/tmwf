package com.taimeitech.pass.dao.FinanceSystem;


import com.taimeitech.framework.common.po.BasePO;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "AuditForm")
public class AuditFormEntity  extends BasePO {

    @ApiModelProperty(value = "processInstanceId")
    @Column(name = "processInstanceId")
    private String processInstanceId;

    @ApiModelProperty(value = "任务id")
    @Column(name = "taskId")
    private String taskId;

    @ApiModelProperty(value = "节点名称")
    @Column(name = "taskName")
    private String taskName;

    @ApiModelProperty(value = "处理人userId")
    @Column(name = "processUserId")
    private String processUserId;

    @ApiModelProperty(value = "处理人姓名")
    @Column(name = "processUserName")
    private String processUserName;

    @ApiModelProperty(value = "审批结果-是否通过")
    @Column(name = "approved")
    private boolean approved;

    @ApiModelProperty(value = "审批意见")
    @Column(name = "examinationComment")
    private String examinationComment;

    @ApiModelProperty(value = "处理日期")
    @Column(name = "processDate")
    private Date processDate;

    @ApiModelProperty(value = "任务分配日期")
    @Column(name = "task_assign_date")
    private Date task_assign_date;

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Date getTask_assign_date() {
        return task_assign_date;
    }

    public void setTask_assign_date(Date task_assign_date) {
        this.task_assign_date = task_assign_date;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
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

    public String getExaminationComment() {
        return examinationComment;
    }

    public void setExaminationComment(String examinationComment) {
        this.examinationComment = examinationComment;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
