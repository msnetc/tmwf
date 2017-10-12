package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

public class RollBackTask implements IReturn<RollBackTaskResponse> {
    @ApiModelProperty(value = "要恢复的历史任务id")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
