package com.taimeitech.pass.entity.workflow.form;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.form.FormProperty;

import java.util.ArrayList;
import java.util.List;

public class tmTaskFormData {
    @ApiModelProperty(value = "表单formkey")
    private String formKey;

    @ApiModelProperty(value = "deploymentId")
    private  String deploymentId;

    @ApiModelProperty(value = "表单字段")
    private List<tmFormProperty> formProperties;

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public List<tmFormProperty> getFormProperties() {
        return formProperties;
    }

    public void setFormProperties(List<tmFormProperty> formProperties) {
        this.formProperties = formProperties;
    }
}
