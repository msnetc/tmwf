package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;


public class CompleteUserTask {
    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "传给工作流的变量")
    private Map<String, Object> variables;

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

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
