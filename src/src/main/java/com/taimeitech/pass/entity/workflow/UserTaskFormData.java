package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;

public class UserTaskFormData {
    @ApiModelProperty(value = "formData")
    private TaskFormData formData;

    public TaskFormData getFormData() {
        return formData;
    }

    public void setFormData(TaskFormData formData) {
        this.formData = formData;
    }


}
