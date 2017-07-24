package com.taimeitech.pass.entity.workflow;


import io.swagger.annotations.ApiModelProperty;

public class CompleteTask {
    @ApiModelProperty(value = "任务id")
    private String taskId;
    @ApiModelProperty(value = "用户id")
    private String userId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
