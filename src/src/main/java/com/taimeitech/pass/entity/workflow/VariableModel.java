package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by yanjie.miao on 2017/8/4.
 */
public class VariableModel {

    @ApiModelProperty(value = "变量名称")
    private String variableName;

    @ApiModelProperty(value = "变量类型")
    private String variableTypeName;

    @ApiModelProperty(value = "变量value")
    private Object variableValue;

    @ApiModelProperty(value = "ProcessInstanceId")
    private String ProcessInstanceId;

    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "lastUpdatedTime")
    private Date lastUpdatedTime;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableTypeName() {
        return variableTypeName;
    }

    public void setVariableTypeName(String variableTypeName) {
        this.variableTypeName = variableTypeName;
    }

    public Object getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(Object variableValue) {
        this.variableValue = variableValue;
    }

    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        ProcessInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
