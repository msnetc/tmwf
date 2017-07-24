package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class tmTask {
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    @ApiModelProperty(value = "任务名称")
    private String name;
    @ApiModelProperty(value = "流程实例ID")
    private String   processInstanceId;
    @ApiModelProperty(value = "任务描述")
    private String Description;
    @ApiModelProperty(value = "owner")
    private String owner;
    @ApiModelProperty(value = "任务的办理人")
    private String assignee;
    @ApiModelProperty(value = "任务创建时间")
    private DateTime createTime;
    @ApiModelProperty(value = "processVariables")
    private Map<String, Object>  processVariables;
    @ApiModelProperty(value = "taskLocalVariables")
    private  Map<String, Object> taskLocalVariables;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    public Map<String, Object> getTaskLocalVariables() {
        return taskLocalVariables;
    }

    public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
        this.taskLocalVariables = taskLocalVariables;
    }
}
