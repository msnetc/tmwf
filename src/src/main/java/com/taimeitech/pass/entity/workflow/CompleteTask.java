package com.taimeitech.pass.entity.workflow;


import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class CompleteTask {
    @ApiModelProperty(value = "任务id")
    private String taskId;
    @ApiModelProperty(value = "用户id")
    private String userId;


    @ApiModelProperty(value = "传给工作流的变量")
    private Map<String, Object> variables;

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

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
