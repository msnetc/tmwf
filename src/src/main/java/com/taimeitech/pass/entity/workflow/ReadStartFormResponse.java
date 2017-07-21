package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;

public class ReadStartFormResponse {

    @ApiModelProperty(value = "是否有formkey属性")
    private ProcessDefinition processDefinition;

    @ApiModelProperty(value = "读取启动流程的表单字段")
    private Object formData;

    @ApiModelProperty(value = "是否有formkey属性")
    private  boolean hasStartFormKey;

    public Object getFormData() {
        return formData;
    }

    public void setFormData(Object formData) {
        this.formData = formData;
    }

    public boolean isHasStartFormKey() {
        return hasStartFormKey;
    }

    public void setHasStartFormKey(boolean hasStartFormKey) {
        this.hasStartFormKey = hasStartFormKey;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }
}
