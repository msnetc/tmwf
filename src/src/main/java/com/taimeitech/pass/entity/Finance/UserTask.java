package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/10.
 */
public class UserTask {

    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;
    @ApiModelProperty(value = "taskId")
    private String taskId;
    @ApiModelProperty(value = "taskName")
    private String taskName;
    @ApiModelProperty(value = "businessKeyId")
    private String businessKeyId;

    @ApiModelProperty(value = "任务状态 0 未完成 1已完成")
    private int taskStatusId;

    public int getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(int taskStatusId) {
        this.taskStatusId = taskStatusId;
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

    public String getBusinessKeyId() {
        return businessKeyId;
    }

    public void setBusinessKeyId(String businessKeyId) {
        this.businessKeyId = businessKeyId;
    }
}
