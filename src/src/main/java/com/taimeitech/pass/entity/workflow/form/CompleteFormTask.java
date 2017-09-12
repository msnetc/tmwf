package com.taimeitech.pass.entity.workflow.form;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;


public class CompleteFormTask implements IReturn<CompleteFormTaskResponse>{
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "表单参数")
    private Map<String, String> parameters;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
