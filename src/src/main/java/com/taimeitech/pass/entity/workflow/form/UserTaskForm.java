package com.taimeitech.pass.entity.workflow.form;

import com.taimeitech.pass.entity.workflow.tmTask;
import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;

public class UserTaskForm {

    @ApiModelProperty(value = "formData")
    private Object formData;
    @ApiModelProperty(value = "是否有外置表单")
    private  boolean hasFormKey;
    @ApiModelProperty(value = "任务信息")
    private tmTask task;

    public boolean isHasFormKey() {
        return hasFormKey;
    }

    public void setHasFormKey(boolean hasFormKey) {
        this.hasFormKey = hasFormKey;
    }

    public tmTask getTask() {
        return task;
    }

    public void setTask(tmTask task) {
        this.task = task;
    }

    public Object getFormData() {
        return formData;
    }

    public void setFormData(Object formData) {
        this.formData = formData;
    }
}
