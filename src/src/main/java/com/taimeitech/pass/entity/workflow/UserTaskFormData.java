package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.task.Task;

public class UserTaskFormData {
    @ApiModelProperty(value = "formData")
    private Object formData;
    @ApiModelProperty(value = "task的数据")
    private Task task;

    public Object getFormData() {
        return formData;
    }

    public void setFormData(Object formData) {
        this.formData = formData;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
